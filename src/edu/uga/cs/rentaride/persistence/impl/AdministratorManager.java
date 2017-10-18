package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

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

public class AdministratorManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public AdministratorManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Administrator administrator) throws RARException{
		String insertAdministratorSql = "insert into user ( type, firstName, lastName, userName, password, email, address, createdDate ) values ( 'Administrator', ?, ?, ?, ?, ?, ?, ? )";
		String updateAdministratorSql = "update person  set type = 'Administrator', firstName = ?, lastName = ?, userName = ?, password = ?, email = ?, address = ?, createdDate = ? where id = ?";
		PreparedStatement stmt;
		int inscnt;
		long userId;
		
		try {
			if(!administrator.isPersistent())
				stmt = (PreparedStatement) conn.prepareStatement(insertAdministratorSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateAdministratorSql);
		}catch() {
			
		}//try catch
	
	}//store
	
	public List<Administrator> restore(Administrator administrator) throws RARException{
		//TODO
		return null;
	}//restore
	
	public void delete(Administrator administrator) throws RARException{
		//TODO
	}//delete
	
}//AdministratorManager
