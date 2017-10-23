package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.entity.impl.RentalLocationImpl;
import edu.uga.cs.rentaride.entity.impl.ReservationImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleImpl;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;

public class RentalManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public RentalManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Rental rental) throws RARException{
		String insertRentalSql = "insert into rental (pickup, dropoff, late, charges, reservationid, vehicleid, userid) values ( ?, ?, ?, ?, ?, ?, ? )";
		String updateRentalSql = "update person  set  pickup = ?, dropoff = ?, late = ?, charges = ?, reservationid = ?, vehicleid = ?, userid = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long rentalId;
		
		if(rental.getReservation() == null)
			throw new RARException ("RentalManager.save: Attempting ot save a Rental with no Reservation defined");
		if(rental.getVehicle() == null)
			throw new RARException ("RentalManager.save: Attempting ot save a Rental with no Vehicle defined");
		if(rental.getCustomer() == null)
			throw new RARException ("RentalManager.save: Attempting ot save a Rental with no Customer defined");
		if(!rental.getReservation().isPersistent())	
			throw new RARException ("RentalManager.save: Attempting ot save a Rental Where Reservation is not persistent");
		if(!rental.getVehicle().isPersistent())	
			throw new RARException ("RentalManager.save: Attempting ot save a Rental Where Vehicle is not persistent");
		if(!rental.getCustomer().isPersistent())	
			throw new RARException ("RentalManager.save: Attempting ot save a Rental Where Customer is not persistent");
		
		try {
	
			if(!rental.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertRentalSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateRentalSql);
		
			if(rental.getPickupTime() != null)
					stmt.setDate(1,new java.sql.Date(rental.getPickupTime().getTime()));
			else
					stmt.setNull(1,  java.sql.Types.DATE);
			
			if(rental.getReturnTime() != null)
				stmt.setDate(2,new java.sql.Date(rental.getReturnTime().getTime()));
			else
				stmt.setNull(2,  java.sql.Types.DATE);
			
			stmt.setBoolean(3,rental.getLate());
			
			if(rental.getCharges() > 0)
				stmt.setInt(4,rental.getCharges());
			else
				stmt.setNull(4, java.sql.Types.INTEGER);
			
			stmt.setLong(5,  rental.getReservation().getId());
			stmt.setLong(6,  rental.getVehicle().getId());
			stmt.setLong(7,  rental.getCustomer().getId());
			
			if(rental.isPersistent())
				stmt.setLong(8,  rental.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!rental.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							rentalId = r.getLong(1);
							if(rentalId > 0)
								rental.setId(rentalId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("RentalManager.save: failed to save a rental");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("RentalManager.save: Failed to save a Rental: " + e);
		}//try catch
	}//store
	
	public List<Rental> restore(Rental rental) throws RARException{

		{
			String       selectRentalSql = "select id, pickup, dropoff, late, charges," +
					"reservationid, vehicleid ,userid from rental";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<Rental> rentals = new ArrayList<>();

			condition.setLength( 0 );

			// form the query based on the given Person object instance
			query.append( selectRentalSql );
			if(rental != null){
				if(rental.getId() >= 0)		//vehicle id is unique
					query.append(" where id = " + rental.getId());
				else {

					if( rental.getPickupTime() != null )
						condition.append( " pickup = '" + rental.getPickupTime() + "'" );

					if( rental.getReturnTime() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " dropoff = '" + rental.getReturnTime() + "'" );
					}


					if( condition.length() > 0 )
						condition.append( " and" );
					condition.append( " late = '" + rental.getLate() + "'" );

					if( rental.getCharges() >= 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " charges = '" + rental.getCharges() + "'" );
					}
					if( rental.getReservation().getId() >= 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " reservationid = '" + rental.getReservation().getId() + "'" );
					}

					if( rental.getVehicle().getId() >= 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " vehicleid = '" + rental.getVehicle().getId() + "'" );
					}
					if( rental.getCustomer().getId() >= 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " userid = '" + rental.getCustomer().getId() + "'" );
					}

					if( condition.length() > 0 ) {
						query.append(  " where " );
						query.append( condition );
					}
				}
			}

			try {

				stmt = conn.createStatement();

				// retrieve the persistent Administrator objects
				//
				if( stmt.execute( query.toString() ) ) { // statement returned a result
					ResultSet rs = stmt.getResultSet();

					long id;
					Date pickupDate;
					Date returnDate;
					boolean late;
					int charges;
					int customerid;
					int reservationid;
					int vehicleid;

					Customer customer;
					Reservation reservation;
					Vehicle vehicle;
					Rental rental1;


					while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
						id = rs.getInt(1);
						pickupDate = rs.getDate(2);
						returnDate = rs.getDate(3);
						late = rs.getBoolean(4);
						charges = rs.getInt(5);
						customerid = rs.getInt(6);
						reservationid= rs.getInt(7);
						vehicleid = rs.getInt(8);

						PersistenceLayer persistenceLayer = Persistence.getPersistencvalayer();

						Customer modelCustomer = new CustomerImpl();
						modelCustomer.setId(customerid);
						modelCustomer = persistenceLayer.restoreCustomer(modelCustomer).get(0);

						Reservation modelReservation = new ReservationImpl();
						modelReservation.setId(reservationid);
						modelReservation = persistenceLayer.restoreReservation(modelReservation).get(0);

						Vehicle modelVehicle = new VehicleImpl();
						modelVehicle.setId(vehicleid);
						modelVehicle = persistenceLayer.restoreVehicle(modelVehicle).get(0);


						rental1 = objectLayer.createRental(pickupDate, modelReservation, modelVehicle);
						rental1.setId(id);

						rentals.add( rental1);

					}

					return rentals;
				}
			}
			catch( Exception e ) {      // just in case...
				throw new RARException( "RentalManager.restore: Could not restore persistent Rental object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "RentalManager.restore: Could not restore persistent Rental objects" );
		}
	}//restore
	
	public void delete(Rental rental) throws RARException{
		
		String deleteRentalSql = "delete from rental where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !rental.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteRentalSql );         
		            stmt.setLong( 1, rental.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "RentalManager.delete: failed to delete a rental" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "RentalManager.delete: failed to delete a rental: " + e );       
		            }
		
		
		
		
		
		
	}//delete
	
}//RentalManager