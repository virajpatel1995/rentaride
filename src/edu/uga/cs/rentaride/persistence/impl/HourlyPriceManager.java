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

public class HourlyPriceManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public HourlyPriceManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(HourlyPrice hourlyPrice) throws RARException{
		String insertHourlyPriceSql = "insert into user ( maxHrs, price, vehicleTypeid ) values ( ?, ?, ? )";
		String updateHourlyPriceSql = "update person  set maxHrs = ?, price = ?, vehicleTypeid = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long hourlyPriceId;
		
		if(hourlyPrice.getVehicleType() == null)
			throw new RARException ("HourlyPriceManager.save: Attempting ot save a HourlyPrice with no VehicleType defined");
		if(!hourlyPrice.getVehicleType().isPersistent())	
			throw new RARException ("HourlyPriceManager.save: Attempting ot save a HourlyPrice with no VehicleType is not persistent");
		
		try {
	
			if(!hourlyPrice.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertHourlyPriceSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateHourlyPriceSql);
		
			if(hourlyPrice.getMaxHours() < 0)
					stmt.setInt(1,hourlyPrice.getMaxHours());
			else
					throw new RARException("HourlyPriceManager.save: can't save a Customer: FirstName undefined");

			if(customer.getLastName() != null)
					stmt.setString(3,customer.getLastName());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: LastName undefined");

			if(customer.getUserName() != null)
					stmt.setString(4,customer.getUserName());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: UserName undefined");

			if(customer.getPassword() != null)
					stmt.setString(5,customer.getPassword());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: Password undefined");

			if(customer.getEmail() != null)
					stmt.setString(6,customer.getEmail());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: Email undefined");

			if(customer.getAddress() != null)
					stmt.setString(7,customer.getAddress());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: Address undefined");

			if(customer.getCreatedDate() != null)
					stmt.setDate(8,customer.getCreatedDate());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: Created Date undefined");
			
			if(customer.getMemberUntil() != null)
				stmt.setDate(9,customer.getMemberUntil());
			else
				throw new RARException("CustomerManager.save: can't save a Customer: Member Until undefined");
		
			if(customer.getLicenseState() != null)
				stmt.setString(10,customer.getLicenseState());
			else
				throw new RARException("CustomerManager.save: can't save a Customer: License State undefined");
			
			if(customer.getLicenseNumber() != null)
				stmt.setString(11,customer.getLicenseNumber());
			else
				throw new RARException("CustomerManager.save: can't save a Customer: License Number undefined");
			
			if(customer.getCreditCardNumber() != null)
				stmt.setString(12,customer.getCreditCardNumber());
			else
				throw new RARException("CustomerManager.save: can't save a Customer: Credit Card Number undefined");

			if(customer.getCreditCardExpiration() != null)
				stmt.setDate(13,customer.getCreditCardExpiration());
			else
				throw new RARException("CustomerManager.save: can't save a Customer: Credit Card Expiration undefined");

			if(customer.getUserStatus() != null)
				stmt.setString(14,customer.getUserStatus().toString());
			else
				throw new RARException("CustomerManager.save: can't save a Customer: User Status undefined");
			
			if(customer.isPersistent())
				stmt.setLong(15,  customer.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!customer.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							userId = r.getLong(1);
							if(userId > 0)
								customer.setId(userId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("CustomerManager.save: failed to save a Customer");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("CustomerManager.save: Failed to save a Customer: " + e);
		}//try catch
	}//store
	
	public List<HourlyPrice> restore(HourlyPrice hourlyPrice) throws RARException{
		//TODO
		return null;
	}//restore
	
	public void delete(HourlyPrice HourlyPrice) throws RARException{
		
		String deleteHourlyPriceSql = "delete from HourlyPrice where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !HourlyPrice.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteHourlyPriceSql );         
		            stmt.setLong( 1, HourlyPrice.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "HourlyPricerManager.delete: failed to delete a HourlyPrice" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "HourlyPriceManager.delete: failed to delete a HourlyPrice: " + e );       
		            }
		
		
		
	}//delete
	
}//HourlyPriceManager