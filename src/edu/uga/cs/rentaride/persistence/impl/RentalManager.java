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

public class RentalManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public RentalManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Rental rental) throws RARException{
		//TODO
	}//store
	
	public List<Rental> restore(Rental rental) throws RARException{
		//TODO
		return null;
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