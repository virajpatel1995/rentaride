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
//        	RentalLocation rentalLocation1 = objectLayer.createRentalLocation("walmart", "1 walmart road", 50);
//        	RentalLocation rentalLocation2 = objectLayer.createRentalLocation("mcdonalds", "1 mcdonalds road", 10);
//        	persistence.storeRentalLocation(rentalLocation1);
//        	persistence.storeRentalLocation(rentalLocation2);

//        	Administrator admin1 = objectLayer.createAdministrator("Shep", "Ogden", "admin1", "sheppassword", "shepogden@uga.edu", "1 Dawg Drive", new Date(System.currentTimeMillis()));
        	Administrator admin2 = objectLayer.createAdministrator("Viraj", "Patel", "admin2", "virajpassword", "virajpatel@uga.edu", "2 Dawg Drive", new Date(System.currentTimeMillis()));
			System.out.println(admin2.getCreatedDate().toString());
			persistence.storeAdministrator(admin2);
//        	Administrator admin1 = objectLayer.createAdministrator("John", null,null,null, null,null, null);
//        	admin1.setId(-1);
//			List<Administrator> administrators = persistence.restoreAdministrator(admin1);
//			for (Administrator admin: administrators) {
//				System.out.println(admin.toString());
//			}

//        	VehicleType vehicleType1 = objectLayer.createVehicleType("truck");
//        	HourlyPrice hourlyPrice1 = objectLayer.createHourlyPrice(10, 5, vehicleType1);
//
//
//
//        	Administrator admin2 = objectLayer.createAdministrator("Viraj", "Patel", "admin2", "virajpassword", "virajpatel@uga.edu", "2 Dawg Drive", new Date("2017-10-08 10:00:00"));
//
//
//
//        	VehicleType vehicleType2 = objectLayer.createVehicleType("van");
//        	HourlyPrice hourlyPrice2 = objectLayer.createHourlyPrice(20, 3, vehicleType2);
//
//        	Vehicle vehicle1 = objectLayer.createVehicle("GMC", "Sierra", 1990, "15ZB35", 100000, new Date("2017-08-05 09:00:00"), vehicleType1, rentalLocation1, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
//        	Vehicle vehicle2 = objectLayer.createVehicle("Honda", "Odyssey", 1998, "1SH5F25", 80000, new Date("2015-07-04 09:00:00"), vehicleType2, rentalLocation1, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
//        	Vehicle vehicle3 = objectLayer.createVehicle("Chevrolet", "Silverado", 2015, "8FJFF445", 30000, new Date("2016-09-08 09:00:00"), vehicleType1, rentalLocation2, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
//        	Vehicle vehicle4 = objectLayer.createVehicle("Toyota", "Sienna", 2000, "MDP5325F", 60000, new Date("2017-09-10 09:00:00"), vehicleType2, rentalLocation2, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
//
//        	Customer customer1 = objectLayer.createCustomer("Chase", "Williams", "chasewilliams", "chasepassword", "chase@uga.edu", "3 Dawg Drive", new Date("2016-07-08 10:00:00"), new Date("2017-07-08 10:00:00"), "Georgia", "51DGf52G", "10484835823924", new Date("2018-07-08 10:00:00"));
//        	Customer customer2 = objectLayer.createCustomer("Josh", "Dawson", "joshdawson", "joshpassword", "josh@uga.edu", "4 Dawg Drive", new Date("2017-08-08 10:00:00"), new Date("2018-08-08 10:00:00"), "Georgia", "81FH52F6H", "5471975974927459", new Date("2018-03-06 10:00:00"));
//
//        	Reservation reservation1 = objectLayer.createReservation(new Date("2017-10-18 09:00:00"), 3, vehicleType1, rentalLocation1, customer1);
//        	Reservation reservation2 = objectLayer.createReservation(new Date("2017-10-19 09:00:00"), 4, vehicleType2, rentalLocation1, customer1);
//        	Reservation reservation3 = objectLayer.createReservation(new Date("2017-10-20 09:00:00"), 5, vehicleType1, rentalLocation2, customer2);
//        	Reservation reservation4 = objectLayer.createReservation(new Date("2017-10-21 09:00:00"), 5, vehicleType2, rentalLocation2, customer2);
//
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
