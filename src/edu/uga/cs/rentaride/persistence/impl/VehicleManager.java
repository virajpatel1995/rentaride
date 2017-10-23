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
import edu.uga.cs.rentaride.entity.*;
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
		//TODO
	}//store
	
	public List<Vehicle> restore(Vehicle vehicle) throws RARException{
		{
			String       selectVehicleSql = "select v.id, make, model, year, mileage, tag, " +
					"lastServiced, status, maintenance, rentalLocationid, vehicleTypeid," +
					"vt.name, rl.name, rl.address, rl.capacity " +
					"from vehicle v, vehicleType vt, rentalLocation rl " +
					"where rentalLocationid = rl.id and  vehicleTypeid = vt.id";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<Vehicle> vehicles= new ArrayList<>();

			condition.setLength( 0 );

			// form the query based on the given Person object instance
			query.append( selectVehicleSql );
			if(vehicle != null){
				if(vehicle.getId() >= 0)		//vehicle id is unique
					query.append(" where id = " + vehicle.getId());
				else if (vehicle.getRegistrationTag() != null) // vehicle username is unique
					query.append(" where tag = '" + vehicle.getRegistrationTag() + "'");
				else {

					if( vehicle.getMake() != null )
						condition.append( " make = '" + vehicle.getMake() + "'" );

					if( vehicle.getModel() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " model = '" + vehicle.getModel() + "'" );
					}

					if( vehicle.getYear() != 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " year = '" + vehicle.getYear() + "'" );
					}

					if( vehicle.getMileage() != 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " millage = '" + vehicle.getMileage() + "'" );
					}

					if( vehicle.getLastServiced() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " lastServiced = '" + vehicle.getLastServiced() + "'" );
					}

					if( vehicle.getStatus() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " status = '" + vehicle.getStatus() + "'" );
					}
					if( vehicle.getCondition() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " maintenance = '" + vehicle.getCondition() + "'" );
					}
					if( vehicle.getRentalLocation().getId() > 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " rentalLocationid = '" + vehicle.getRentalLocation().getId() + "'" );
					}
					if( vehicle.getVehicleType().getId() > 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " vehicleTypeid = '" + vehicle.getVehicleType().getId() + "'" );
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
					String			 make;
					String			 model;
					String 			 registrationTag;
					int				 year;
					int				 mileage;
					Date			 lastServiced;
					VehicleStatus 	 status;
					VehicleCondition vehicleCondition;
					int rentalLocationid;
					int vehicleTypeid;
					String 			 vtname;
					String 			 rlname;
					String 			 rladdress;
					int capacity;

					VehicleType		 vehicleType;
					RentalLocation   rentalLocation;

					while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
						id = rs.getLong( 1 );
						make = rs.getString( 2 );
						model = rs.getString( 3 );
						registrationTag = rs.getString( 4 );
						year = rs.getInt( 5 );
						mileage = rs.getInt( 6 );
						lastServiced = rs.getDate( 7 );
						status = VehicleStatus.valueOf(rs.getString( 8 ));
						vehicleCondition = VehicleCondition.valueOf(rs.getString( 9 ));
						rentalLocationid = rs.getInt(10);
						vehicleTypeid = rs.getInt(11);
						vtname = rs.getString( 12 );
						rlname = rs.getString( 13 );
						rladdress = rs.getString( 14 );
						capacity = rs.getInt( 15 );

						vehicleType = objectLayer.createVehicleType(vtname);
						vehicleType.setId(vehicleTypeid);
						rentalLocation = objectLayer.createRentalLocation(rlname, rladdress, capacity);
						rentalLocation.setId(rentalLocationid);
						Vehicle vehicle1 = objectLayer.createVehicle(make, model, year, registrationTag, mileage, lastServiced, vehicleType, rentalLocation, vehicleCondition, status);
						vehicle1.setId( id );

						vehicles.add( vehicle1 );

					}

					return vehicles;
				}
			}
			catch( Exception e ) {      // just in case...
				throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator objects" );
		}
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