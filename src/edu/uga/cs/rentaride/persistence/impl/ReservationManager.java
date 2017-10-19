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
		//TODO
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