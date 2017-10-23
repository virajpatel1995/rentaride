package edu.uga.cs.rentaride.test;

import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.DbUtils;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

public class RentARideTester {

	public static void main(String[] args) throws RARException {
		Connection  conn = null;
        ObjectLayer objectLayer = null;
        PersistenceLayer persistence = null;

        // get a database connection                                                                     
        try {
            conn = DbUtils.connect();
        }
        catch(Exception seq) {
            System.err.println( "ReadTest: Unable to obtain a database connection" );
        }

        if( conn == null ) {
            System.out.println( "ReadTest: failed to connect to the database" );
            return;
        }

        // connect the ObjectModel module to the Persistence module
        objectLayer = new ObjectLayerImpl(persistence);
        // obtain a reference to Persistence module and connect it to the ObjectModel
        persistence = new PersistenceLayerImpl( conn, objectLayer );

        try {
        	 ///ergejgerngerjkgnergerkjnger
        	
        	/*
        	 * 
        	 *  Create 2 rental locations.
        	 *  
        	 *  
        	 *  */
        	
       	RentalLocation rentalLocation1 = objectLayer.createRentalLocation("Tatex", "1 walmart road", 50);
       	RentalLocation rentalLocation2 = objectLayer.createRentalLocation("Boyd", "1 mcdonalds road", 10);
       	persistence.storeRentalLocation(rentalLocation1);
       	persistence.storeRentalLocation(rentalLocation2);
       	
	    	/*
	    	 * 
	    	 *  Create 2 rental locations.Create 2 administrators.
	    	 *  
	    	 *  
	    	 *  */
       	
        	Administrator admin1 = objectLayer.createAdministrator("Shep", "Patel", "admin1", "sheppassword", "shepogden@uga.edu", "1 Dawg Drive", new Date(System.currentTimeMillis()));
        	Administrator admin2 = objectLayer.createAdministrator("Viraj", "Patel", "admin2", "virajpassword", "virajpatel@uga.edu", "2 Dawg Drive", new Date(System.currentTimeMillis()));
    		persistence.storeAdministrator(admin1);
		persistence.storeAdministrator(admin2);
			
	
		/*
	    	 * 
	    	 *  Create 2 vehicle types, each with 2 different hourly prices.
	
	    	 *  
	    	 *  
	    	 *  */
    	
        	VehicleType vehicleType1 = objectLayer.createVehicleType("truck");
        	HourlyPrice hourlyPrice1 = objectLayer.createHourlyPrice(10, 5, vehicleType1);
        	persistence.storeHourlyPrice(hourlyPrice1);

        	VehicleType vehicleType2 = objectLayer.createVehicleType("van");
        	HourlyPrice hourlyPrice2 = objectLayer.createHourlyPrice(20, 3, vehicleType2);
        	persistence.storeHourlyPrice(hourlyPrice2);

        	
        	/*
	    	 * 
	    	 *  Create 4 vehicles, 2 of one vehicle type and 2 of the other, assigned to the 2 rental
			locations (2 vehicles per location but with different vehicle types at each location).
	    	 *  
	    	 *  
	    	 *  */
       
 
       	Vehicle vehicle1 = objectLayer.createVehicle("GMC", "Sierra", 1990, "15ZB35", 100000, new Date(System.currentTimeMillis()), vehicleType1, rentalLocation1, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
        	Vehicle vehicle2 = objectLayer.createVehicle("Honda", "Odyssey", 1998, "1SH5F25", 80000, new Date(System.currentTimeMillis()), vehicleType2, rentalLocation1, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
        	Vehicle vehicle3 = objectLayer.createVehicle("Chevrolet", "Silverado", 2015, "8FJFF445", 30000, new Date(System.currentTimeMillis()), vehicleType1, rentalLocation2, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
        	Vehicle vehicle4 = objectLayer.createVehicle("Toyota", "Sienna", 2000, "MDP5325F", 60000, new Date(System.currentTimeMillis()), vehicleType2, rentalLocation2, VehicleCondition.GOOD, VehicleStatus.INLOCATION);

        	persistence.storeVehicle(vehicle1);
        	persistence.storeVehicle(vehicle2);
        	persistence.storeVehicle(vehicle3);
        	persistence.storeVehicle(vehicle4);

        	
        	
        	
        	/*
	    	 * 
	    	 *  Create 2 Customers.
	    	 *  
	    	 *  
	    	 *  */
        	
//            Customer customer1 = objectLayer.createCustomer("Chase", "Williams", "chasewilliams", "chasepassword", "chase@uga.edu", "3 Dawg Drive", new Date(System.currentTimeMillis()), new Date("2017-07-08 10:00:00"), "Georgia", "51DGf52G", "10484835823924", new Date("2018-07-08 10:00:00"), null);
//            Customer customer2 = objectLayer.createCustomer("Josh", "Dawson", "joshdawson", "joshpassword", "josh@uga.edu", "4 Dawg Drive", new Date("2017-08-08 10:00:00"), new Date("2018-08-08 10:00:00"), "Georgia", "81FH52F6H", "5471975974927459", new Date("2018-03-06 10:00:00"), null);
//
//    		  persistence.storeCustomer(customer1);
//    		  persistence.storeCustomer(customer2);

        	
     
        	/*
	    	 * 
	    	 *  For each customer, create 2 reservations, each with a vehicle type and a rental location.
	    	 *  
	    	 *  
	    	 *  */    	
        	
     	
    
//     	    Reservation reservation1 = objectLayer.createReservation(new Date("2017-10-18 09:00:00"), 3, vehicleType1, rentalLocation1, customer1);
//        	Reservation reservation2 = objectLayer.createReservation(new Date("2017-10-19 09:00:00"), 4, vehicleType2, rentalLocation1, customer1);
//        	Reservation reservation3 = objectLayer.createReservation(new Date("2017-10-20 09:00:00"), 5, vehicleType1, rentalLocation2, customer2);
//        	Reservation reservation4 = objectLayer.createReservation(new Date("2017-10-21 09:00:00"), 5, vehicleType2, rentalLocation2, customer2);
//
//        	persistence.storeReservation(reservation1);
//        	persistence.storeReservation(reservation2);
//        	persistence.storeReservation(reservation3);
//        	persistence.storeReservation(reservation4);

        	
        	
        	
        	/*
	    	 * 
	    	 *  For two of the created reservations (one per customer), create corresponding rentals, each involving a vehicle (with a correct vehicle type) and a comment. Do not calculate the
            rental charges or late fees but include some “made-up” values.
	    	 *  
	    	 *  
	    	 *  */
        	
        	
        	
        	
        	
//        	Rental rental1 = objectLayer.createRental(new Date("2017-10-18 09:00:00"), reservation1, vehicle1);
//        	Rental rental2 = objectLayer.createRental(new Date("2017-10-20 09:00:00"), reservation3, vehicle2);
//
//        	Comment comment1 = objectLayer.createComment("Great car", new Date("2017-10-19 09:00:00"), rental1, customer1);
//        	Comment comment2 = objectLayer.createComment("Best car ever", new Date("2017-10-21 09:00:00"), rental2, customer2);
//
//        	objectLayer.deleteComment(comment2);
//        	objectLayer.deleteComment(comment1);
//
//        	objectLayer.deleteRental(rental2);
//        	objectLayer.deleteRental(rental1);
//
//        	objectLayer.deleteReservation(reservation4);
//        	objectLayer.deleteReservation(reservation3);
//        	objectLayer.deleteReservation(reservation2);
//        	objectLayer.deleteReservation(reservation1);
//
//        	//NOT IMPLEMENTED?????
//        	//objectLayer.deleteCustomer(customer2);
//        	//objectLayer.deleteCustomer(customer1);
//
//        	objectLayer.deleteVehicle(vehicle4);
//        	objectLayer.deleteVehicle(vehicle3);
//        	objectLayer.deleteVehicle(vehicle2);
//        	objectLayer.deleteVehicle(vehicle1);
//
//        	objectLayer.deleteHourlyPrice(hourlyPrice2);
//        	objectLayer.deleteVehicleType(vehicleType2);
//        	objectLayer.deleteHourlyPrice(hourlyPrice1);
//        	objectLayer.deleteVehicleType(vehicleType1);
//
//        	objectLayer.deleteRentalLocation(rentalLocation2);
//        	objectLayer.deleteRentalLocation(rentalLocation1);
//
//        	objectLayer.deleteAdministrator(admin2);
//        	objectLayer.deleteAdministrator(admin1);
        	
        }
        catch( RARException ce)
        {
            System.err.println( "RARException: " + ce );
        }
        catch( Exception e)
        {
            System.out.flush();
            System.err.println( "Exception: " + e );
        }
        finally {
            // close the connection!!!                                                                   
            try {
                conn.close();
            }
            catch( Exception e ) {
                System.err.println( "Exception: " + e );
            }
        }
	}
}
