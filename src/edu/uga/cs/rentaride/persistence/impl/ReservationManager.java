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

public class ReservationManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public ReservationManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Reservation reservation) throws RARException{
		String insertReservationSql = "insert into reservation (pickup, length, canceled, userid, rentalLocationid, vehcileTypeid) values ( ?, ?, ?, ?, ?, ? )";
		String updateReservationSql = "update person  set  pickup = ?, length = ?, canceled = ?, userid = ?, rentalLocationid = ?, vehicleTypeid = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long reservationId;
		
		if(reservation.getRentalLocation() == null)
			throw new RARException ("Reservation.save: Attempting ot save a Reservation with no RentalLocation defined");
		if(reservation.getVehicleType() == null)
			throw new RARException ("Reservation.save: Attempting ot save a Reservation with no VehicleType defined");
		if(reservation.getCustomer() == null)
			throw new RARException ("Reservation.save: Attempting ot save a Rental with no Reservation defined");
		if(!reservation.getRentalLocation().isPersistent())	
			throw new RARException ("Reservation.save: Attempting ot save a Rental Where RentalLocation is not persistent");
		if(!reservation.getVehicleType().isPersistent())	
			throw new RARException ("Reservation.save: Attempting ot save a Rental Where VehicleType is not persistent");
		if(!reservation.getCustomer().isPersistent())	
			throw new RARException ("Reservation.save: Attempting ot save a Reservation Where Customer is not persistent");
		
		try {
	
			if(!reservation.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertReservationSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateReservationSql);
		
			if(reservation.getPickupTime() != null)
				stmt.setDate(1,new java.sql.Date(reservation.getPickupTime().getTime()));
			else
				throw new RARException("ResevrationManager.save: can't save a Reservation: Pickup Time undefined");

			if(reservation.getLength() > 0)
				stmt.setInt(2,reservation.getLength());
			else
				throw new RARException("ResevrationManager.save: can't save a Reservation: Length undefined");
			
			//TODO
			//stmt.setBoolean(3,reservation.get);

			stmt.setLong(4,  reservation.getCustomer().getId());
			stmt.setLong(5,  reservation.getRentalLocation().getId());
			stmt.setLong(6,  reservation.getVehicleType().getId());
			
			if(reservation.isPersistent())
				stmt.setLong(8, reservation.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!reservation.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							reservationId = r.getLong(1);
							if(reservationId > 0)
								reservation.setId(reservationId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("ReservationManager.save: failed to save a reservation");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("ReservationManager.save: Failed to save a Reservation: " + e);
		}//try catch
	}//store
	
	public List<Reservation> restore(Reservation reservation) throws RARException{
		//TODO
		return null;
	}//restore
	
	public void delete(Reservation reservation) throws RARException{
		
		String deleteReservationSql = "delete from reservation where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !reservation.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteReservationSql );         
		            stmt.setLong( 1, reservation.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "ReservationManager.delete: failed to delete a reservation" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "ReservationManager.delete: failed to delete a reservation: " + e );       
		            }
		
		
		
	}//delete
	
}//ReservationManager