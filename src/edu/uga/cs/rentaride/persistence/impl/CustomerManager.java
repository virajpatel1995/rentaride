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
	
	public void store(Customer customer) {
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
					throw new RARException("CustomerManager.save: can't save an Customer: FirstName undefined");

			if(customer.getLastName() != null)
					stmt.setString(3,customer.getLastName());
			else
					throw new RARException("CustomerManager.save: can't save an Customer: LastName undefined");

			if(customer.getUserName() != null)
					stmt.setString(4,customer.getUserName());
			else
					throw new RARException("CustomerManager.save: can't save an Customer: UserName undefined");

			if(customer.getPassword() != null)
					stmt.setString(5,customer.getPassword());
			else
					throw new RARException("CustomerManager.save: can't save an Customer: Password undefined");

			if(customer.getEmail() != null)
					stmt.setString(6,customer.getEmail());
			else
					throw new RARException("CustomerManager.save: can't save an Customer: Email undefined");

			if(customer.getAddress() != null)
					stmt.setString(7,customer.getAddress());
			else
					throw new RARException("CustomerManager.save: can't save an Customer: Address undefined");

			if(customer.getCreatedDate() != null)
					stmt.setString(8,customer.getCreatedDate().toString());
			else
					throw new RARException("CustomerManager.save: can't save an Customer: Created Date undefined");
			
			if(customer.getMemberUntil() != null)
				stmt.setString(8,customer.getMemberUntil().toString());
			else
				throw new RARException("CustomerManager.save: can't save an Customer: Member Until undefined");
		
			if(customer.getLicenseState() != null)
				stmt.setString(8,customer.getLicenseState());
			else
				throw new RARException("CustomerManager.save: can't save an Customer: License State undefined");
			
			if(customer.getLicenseNumber() != null)
				stmt.setString(7,customer.getLicenseNumber());
			else
				throw new RARException("CustomerManager.save: can't save an Customer: License Number undefined");
			
			if(customer.getCreditCardNumber() != null)
				stmt.setString(7,customer.getCreditCardNumber());
			else
				throw new RARException("CustomerManager.save: can't save an Customer: Credit Card Number undefined");

			if(customer.getCreditCardExpiration() != null)
				stmt.setString(7,customer.getCreditCardExpiration().toString());
			else
				throw new RARException("CustomerManager.save: can't save an Customer: Credit Card Expiration undefined");

			if(customer.isPersistent())
				stmt.setLong(9,  customer.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!administrator.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							userId = r.getLong(1);
							if(userId > 0)
								administrator.setId(userId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("AdministratorManager.save: failed to save an Administrator");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("AdministratorManager.save: Failed to save an Administrator: " + e);
		}//try ctach
	
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