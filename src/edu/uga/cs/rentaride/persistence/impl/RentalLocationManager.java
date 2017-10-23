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
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class RentalLocationManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public RentalLocationManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(RentalLocation rentalLocation) throws RARException{
		String insertRentalLocationSql = "insert into rentalLocation ( name, address, capacity) values ( ?, ?, ?)";
		String updateRentalLocationSql = "update person  set name = ?, address = ?, capacity = ?, where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long rentalLocationId;
		
		try {
	
			if(!rentalLocation.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertRentalLocationSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateRentalLocationSql);
			
			if(rentalLocation.getName() != null)
					stmt.setString(1,rentalLocation.getName());
			else
					throw new RARException("RentalLocationManager.save: can't save a Rental Location: Name undefined");

			if(rentalLocation.getAddress() != null)
					stmt.setString(2,rentalLocation.getAddress());
			else
					throw new RARException("RentalLocationManager.save: can't save an Rental Location: Address undefined");

			if(rentalLocation.getCapacity() > 0)
					stmt.setInt(3,rentalLocation.getCapacity());
			else
					throw new RARException("RentalLocationManager.save: can't save a RentalLocation: Capacity undefined");

			if(rentalLocation.isPersistent())
				stmt.setLong(4,  rentalLocation.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!rentalLocation.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							rentalLocationId = r.getLong(1);
							if(rentalLocationId > 0)
								rentalLocation.setId(rentalLocationId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("RentalLocationManager.save: failed to save a Rental Location");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("RentalLocationManager.save: Failed to save a Rental Location: " + e);
		}//try catch
	
	}//store
	
	public List<RentalLocation> restore(RentalLocation rentalLocation) throws RARException{
		{
			String       selectRentalLocationSql =
					"select id, name, address, capacity " +
					"from rentalLocation ";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<RentalLocation> rentalLocations = new ArrayList<>();

			condition.setLength( 0 );

			// form the query based on the given Person object instance
			query.append( selectRentalLocationSql );
			if(rentalLocation != null){
				if(rentalLocation.getId() >= 0)
					query.append(" where id = " + rentalLocation.getId());
				else if(rentalLocation.getName() != null)
					query.append(" where name = " + rentalLocation.getName());
				else{
					if( rentalLocation.getAddress() != null )
						if( condition.length() > 0 )
							condition.append( " and" );
					condition.append( " address = '" + rentalLocation.getAddress() + "'" );

					if( rentalLocation.getCapacity() >= 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " capacity = '" + rentalLocation.getCapacity() + "'" );
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
					String name;
					String address;
					int capacity;

					while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
						id = rs.getLong( 1);
						name = rs.getString(2);
						address = rs.getString(3);
						capacity = rs.getInt( 4);

						RentalLocation rentalLocation1 = objectLayer.createRentalLocation(name, address, capacity);
						rentalLocation1.setId( id );

						rentalLocations.add( rentalLocation1 );

					}

					return rentalLocations;
				}
			}
			catch( Exception e ) {      // just in case...
				throw new RARException( "RentalLocationManager.restore: Could not restore persistent RentalLocation object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "RentalLocationManager.restore: Could not restore persistent RentalLocation objects" );
		}
	}//restore
	
	public void delete(RentalLocation rentalLocation) throws RARException{
		
		String deleteRentalLocationSql = "delete from RentalLocation where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !rentalLocation.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteRentalLocationSql );         
		            stmt.setLong( 1, rentalLocation.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "RentalLocationManager.delete: failed to delete a RentalLocation" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "RentalLocationManager.delete: failed to delete a RentalLocation: " + e );       
		            }
		
		
		
		
	}//delete

	public List<Reservation> restoreReservations(RentalLocation rentalLocation) {
	}

	public List<Vehicle> restoreVehicles(RentalLocation rentalLocation) {
	}
}//RentalLocationManager