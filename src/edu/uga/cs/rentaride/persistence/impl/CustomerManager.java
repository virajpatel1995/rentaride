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
		String insertCustomerSql = "insert into user ( type, firstName, lastName, userName, password, email, address, createdDate ) values ( 'Administrator', ?, ?, ?, ?, ?, ?, ? )";
		String updateCustomerSql = "update person  set type = \"Customer\", firstName = ?, lastName = ?, userName = ?, password = ?, email = ?, address = ?, createdDate = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long userId;
		
		try {
			if(!administrator.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertAdministratorSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateAdministratorSql);
		
			if(administrator.getFirstName() != null)
					stmt.setString(1,administrator.getFirstName());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: FirstName undefined");

			if(administrator.getLastName() != null)
					stmt.setString(2,administrator.getLastName());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: LastName undefined");

			if(administrator.getUserName() != null)
					stmt.setString(3,administrator.getUserName());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: UserName undefined");

			if(administrator.getPassword() != null)
					stmt.setString(4,administrator.getPassword());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: Password undefined");

			if(administrator.getEmail() != null)
					stmt.setString(5,administrator.getEmail());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: Email undefined");

			if(administrator.getAddress() != null)
					stmt.setString(6,administrator.getAddress());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: Address undefined");

			if(administrator.getCreatedDate() != null)
					stmt.setString(7,administrator.getCreatedDate().toString());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: FirstName undefined");
		
		}catch (Exception e) {

			if(administrator.isPersistent())
				stmt.setLong(8,  administrator.getId());
		
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
			
		}
	
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