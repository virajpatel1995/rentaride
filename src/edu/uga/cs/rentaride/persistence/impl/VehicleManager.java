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

public class VehicleManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public VehicleManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Vehicle vehicle) throws RARException{
		String insertVehicleSql = "insert into vehicle (make, model, year, mileage, tag, lastServiced, status, maintenance, rentalLocationid, vehicleTypeid) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String updateVehicleSql = "update person  set  make = ?, model = ?, year = ?, mileage = ?, tag = ?, lastServiced = ?, status = ?, maintenance = ?, rentalLocationid = ?, vehicleTypeid = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long vehicleId;
		
		if(vehicle.getRentalLocation() == null)
			throw new RARException ("Vehicle.save: Attempting ot save a Vehicle with no RentalLocation defined");
		if(vehicle.getVehicleType() == null)
			throw new RARException ("Vehicle.save: Attempting ot save a Vehicle with no VehicleType defined");
		if(!vehicle.getRentalLocation().isPersistent())	
			throw new RARException ("Vehicle.save: Attempting ot save a vehcile Where RentalLocation is not persistent");
		if(!vehicle.getVehicleType().isPersistent())	
			throw new RARException ("Vehicle.save: Attempting ot save a Vehicle Where VehicleType is not persistent");
			
		try {
	
			if(!vehicle.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertVehicleSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateVehicleSql);
		
			if(vehicle.getMake() != null)
				stmt.setString(1,vehicle.getMake());
			else
				throw new RARException("VehicleManager.save: can't save a Vehicle: Make undefined");

			if(vehicle.getModel() != null)
				stmt.setString(2,vehicle.getModel());
			else
				throw new RARException("VehicleManager.save: can't save a Vehicle: Model undefined");

			if(vehicle.getYear() > 0)
				stmt.setInt(3,vehicle.getYear());
			else
				throw new RARException("VehicleManager.save: can't save a Vehcile: Year undefined");
			
			if(vehicle.getMileage() > 0)
				stmt.setInt(4,vehicle.getMileage());
			else
				throw new RARException("VehicleManager.save: can't save a Vehcile: Mileage undefined");
			
			//TODO
			/*
			if(vehicle.getTag() != null)
				stmt.setString(5,vehicle.getTag());
			else
				throw new RARException("VehicleManager.save: can't save a Vehicle: Tag undefined");
			*/
			
			if(vehicle.getLastServiced() != null)
				stmt.setDate(6,new java.sql.Date(vehicle.getLastServiced().getTime()));
			else
				stmt.setNull(6,  java.sql.Types.DATE);
			
			if(vehicle.getStatus() != null)
				stmt.setString(7,vehicle.getStatus().toString());
			else
				throw new RARException("VehicleManager.save: can't save a Vehicle: Status undefined");
			
			if(vehicle.getCondition() != null)
				stmt.setString(8,vehicle.getCondition().toString());
			else
				throw new RARException("VehicleManager.save: can't save a Vehicle: Condititon undefined");
			
			stmt.setLong(9,  vehicle.getRentalLocation().getId());
			stmt.setLong(10,  vehicle.getVehicleType().getId());
			
			if(vehicle.isPersistent())
				stmt.setLong(11, vehicle.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!vehicle.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							vehicleId = r.getLong(1);
							if(vehicleId > 0)
								vehicle.setId(vehicleId);
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
	
	public List<Vehicle> restore(Vehicle vehicle) throws RARException{
		//TODO
		return null;
	}//restore
	
	public void delete(Vehicle vehicle) throws RARException{
		
		String deleteVehicleSql = "delete from Vehicle where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !vehicle.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteVehicleSql );         
		            stmt.setLong( 1, vehicle.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "VehicleManager.delete: failed to delete a vehicle" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "VehicleManager.delete: failed to delete a vehicle: " + e );       
		            }
		
		
		
	}//delete
	
}//VehicleManager