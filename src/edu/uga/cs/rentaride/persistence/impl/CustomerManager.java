package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
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

public class CustomerManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public CustomerManager(Connection conn, ObjectLayer objectLayer) throws RARException{
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Customer customer) throws RARException{
		String insertCustomerSql = "insert into user ( type, firstName, lastName, userName, password, email, address, createdDate, memberUntil, licState, licNumber, ccNumber, ccExpiration, status ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		String updateCustomerSql = "update person  set type = ? firstName = ?, lastName = ?, userName = ?, password = ?, email = ?, address = ?, createdDate = ?, memberUntil = ?, licState = ?, licNumber = ?, ccNumber = ?, ccExpiration = ?, status = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long userId;
		
		try {
	
			if(!customer.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertCustomerSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateCustomerSql);
		
			stmt.setString(1,  "Customer");
			
			if(customer.getFirstName() != null)
					stmt.setString(2,customer.getFirstName());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: FirstName undefined");

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
					stmt.setDate(8,new java.sql.Date(customer.getCreatedDate().getTime()));
			else
					throw new RARException("CustomerManager.save: can't save a Customer: Created Date undefined");
			
			if(customer.getMemberUntil() != null)
				stmt.setDate(9,new java.sql.Date(customer.getMemberUntil().getTime()));
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
				stmt.setDate(13,new java.sql.Date(customer.getCreditCardExpiration().getTime()));
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
	
	public List<Customer> restore(Customer customer) throws RARException{
		//TODO
		return null;
	}//restore
	
	/*
	public void delete(Customer customer) throws RARException{
		//TODO
	}//delete
	*/
}//CustomerManager