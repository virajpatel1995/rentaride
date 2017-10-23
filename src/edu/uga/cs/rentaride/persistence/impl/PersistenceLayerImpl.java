package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class PersistenceLayerImpl implements PersistenceLayer {


	//variables for the managers
	private AdministratorManager administratorManager = null;
	private CommentManager commentManager = null;
	private CustomerManager customerManager = null;
	private HourlyPriceManager hourlyPriceManager = null;
	private RentalManager rentalManager = null;
	private RentalLocationManager rentalLocationManager = null;
	private ReservationManager reservationManager = null;
	private VehicleManager vehicleManager = null;
	private VehicleTypeManager vehicleTypeManager = null;
	private RentARideParamsManager rentARideParamsManager = null;
	
	/*
	 * PersistenceLayerImpl Constructor
	 */
	public PersistenceLayerImpl(Connection conn, ObjectLayer objectLayer) throws RARException {
		administratorManager = new AdministratorManager(conn, objectLayer);
		commentManager = new CommentManager(conn, objectLayer);
		customerManager = new CustomerManager(conn, objectLayer);
		hourlyPriceManager = new HourlyPriceManager(conn, objectLayer);
		rentalManager = new RentalManager(conn, objectLayer);
		rentalLocationManager = new RentalLocationManager(conn, objectLayer);
		reservationManager = new ReservationManager(conn, objectLayer);
		vehicleManager = new VehicleManager(conn, objectLayer);
		vehicleTypeManager = new VehicleTypeManager(conn, objectLayer);
		rentARideParamsManager = new RentARideParamsManager(conn, objectLayer);
		System.out.println("PersistenceLayerImpl.PersistenceLayerImpl(conn, objectLayer): Initilized");
	}//Constructor

	@Override
	public List<Administrator> restoreAdministrator(Administrator modelAdministrator) throws RARException {
		return administratorManager.restore(modelAdministrator);
	}//restoreAdministrator

	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		administratorManager.store(administrator);
	}//storeAdministrator

	@Override
	public void deleteAdministrator(Administrator administrator) throws RARException {
		administratorManager.delete(administrator);
	}//deleteAdministrator

	@Override
	public List<Customer> restoreCustomer(Customer modelCustomer) throws RARException {
		return customerManager.restore(modelCustomer);
	}//restoreCustomer

	@Override
	public void storeCustomer(Customer customer) throws RARException {
		customerManager.store(customer);
	}//storeCustomer
	
	/*
	@Override
	public void deleteCustomer(Customer customer) throws RARException{
		customerManager.delete(customer);
	}//deleteCustomer
	 */
	
	@Override
	public List<RentalLocation> restoreRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		return rentalLocationManager.restore(modelRentalLocation);
	}//restoreRentalLocation

	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		rentalLocationManager.store(rentalLocation);
	}//storeRentalLocation

	@Override
	public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException {
		rentalLocationManager.delete(rentalLocation);
	}//deleteRentalLocation

	@Override
	public List<Reservation> restoreReservation(Reservation modelReservation) throws RARException {
		return reservationManager.restore(modelReservation);
	}//restoreReservation

	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		reservationManager.store(reservation);
	}//storeReservation

	@Override
	public void deleteReservation(Reservation reservation) throws RARException {
		reservationManager.delete(reservation);
	}//deleteReservation

	@Override
	public List<Rental> restoreRental(Rental modelRental) throws RARException {
		return rentalManager.restore(modelRental);
	}//restoreRental

	@Override
	public void storeRental(Rental rental) throws RARException {
		rentalManager.store(rental);
	}//storeRental

	@Override
	public void deleteRental(Rental rental) throws RARException {
		rentalManager.delete(rental);
	}//deleteRental

	@Override
	public List<VehicleType> restoreVehicleType(VehicleType modelVehicleType) throws RARException {
		return vehicleTypeManager.restore(modelVehicleType);
	}

	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		vehicleTypeManager.store(vehicleType);
	}

	@Override
	public void deleteVehicleType(VehicleType vehicleType) throws RARException {
		vehicleTypeManager.delete(vehicleType);
	}

	@Override
	public List<Vehicle> restoreVehicle(Vehicle modelVehicle) throws RARException {
		return vehicleManager.restore(modelVehicle);
	}

	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		vehicleManager.store(vehicle);
	}

	@Override
	public void deleteVehicle(Vehicle vehicle) throws RARException {
		vehicleManager.delete(vehicle);
	}

	@Override
	public List<Comment> restoreComment(Comment modelComment) throws RARException {
		return commentManager.restore(modelComment);
	}

	@Override
	public void storeComment(Comment comment) throws RARException {
		commentManager.store(comment);
	}

	@Override
	public void deleteComment(Comment comment) throws RARException {
		commentManager.delete(comment);
	}

	@Override
	public List<HourlyPrice> restoreHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		return hourlyPriceManager.restore(modelHourlyPrice);
	}

	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		hourlyPriceManager.store(hourlyPrice);
	}

	@Override
	public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		hourlyPriceManager.delete(hourlyPrice);
	}

	@Override
	public RentARideParams restoreRentARideConfig() throws RARException {
		return rentARideParamsManager.restore();
	}

	@Override
	public void storeRentARideConfig(RentARideParams rentARideConfig) throws RARException {
		rentARideParamsManager.store(rentARideConfig);
	}

	@Override
	public void storeCustomerReservation(Customer customer, Reservation reservation) throws RARException {
		customerManager.store(customer);
		reservationManager.store(reservation);
	}

	@Override
	public Customer restoreCustomerReservation(Reservation reservation) throws RARException {
		return reservation.getCustomer();
	}

	@Override
	public List<Reservation> restoreCustomerReservation(Customer customer) throws RARException {
		return customer.getReservations();
	}

	@Override
	public void deleteCustomerReservation(Customer customer, Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeReservationRentalLocation(Reservation reservation, RentalLocation rentalLocation)
			throws RARException {
		reservationManager.store(reservation);
		rentalLocationManager.store(rentalLocation);
	}

	@Override
	public RentalLocation restoreReservationRentalLocation(Reservation reservation) throws RARException {
		return reservation.getRentalLocation();
	}

	@Override
	public List<Reservation> restoreReservationRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteReservationRentalLocation(Reservation reservation, RentalLocation rentalLocation)
			throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}

	@Override
	public void storeReservationVehicleType(Reservation reservation, VehicleType vehicleType) throws RARException {
		reservationManager.store(reservation);
		vehicleTypeManager.store(vehicleType);
	}

	@Override
	public VehicleType restoreReservationVehicleType(Reservation reservation) throws RARException {
		return reservation.getVehicleType();
	}

	@Override
	public List<Reservation> restoreReservationVehicleType(VehicleType vehicleType) throws RARException {
		return vehicleType.getReservations();
	}

	@Override
	public void deleteReservationVehicleType(Reservation reservation, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}

	@Override
	public void storeVehicleRentalLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		vehicleManager.store(vehicle);
		rentalLocationManager.store(rentalLocation);
	}

	@Override
	public RentalLocation restoreVehicleRentalLocation(Vehicle vehicle) throws RARException {
		return vehicle.getRentalLocation();
	}

	@Override
	public List<Vehicle> restoreVehicleRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVehicleRentalLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}

	@Override
	public void storeVehicleVehicleType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		vehicleManager.store(vehicle);
		vehicleTypeManager.store(vehicleType);
	}

	@Override
	public VehicleType restoreVehicleVehicleType(Vehicle vehicle) throws RARException {
		return vehicle.getVehicleType();
	}

	@Override
	public List<Vehicle> restoreVehicleVehicleType(VehicleType vehicleType) throws RARException {
		return vehicleType.getVehicles();
	}

	@Override
	public void deleteVehicleVehicleType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}

	@Override
	public void storeVehicleTypeHourlyPrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		vehicleTypeManager.store(vehicleType);
		hourlyPriceManager.store(hourlyPrice);
	}

	@Override
	public VehicleType restoreVehicleTypeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		return hourlyPrice.getVehicleType();
	}

	@Override
	public List<HourlyPrice> restoreVehicleTypeHourlyPrice(VehicleType vehicleType) throws RARException {
		return vehicleType.getHourlyPrices();
	}

	@Override
	public void deleteVehicleTypeHourlyPrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}

	@Override
	public void storeRentalComment(Rental rental, Comment comment) throws RARException {
		rentalManager.store(rental);
		commentManager.store(comment);
	}

	@Override
	public Rental restoreRentalComment(Comment comment) throws RARException {
		return comment.getRental();
	}

	@Override
	public List<Comment> restoreRentalComment(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRentalComment(Rental rental, Comment comment) throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}

	@Override
	public void storeRentalReservation(Rental rental, Reservation reservation) throws RARException {
		rentalManager.store(rental);
		reservationManager.store(reservation);
	}

	@Override
	public Rental restoreRentalReservation(Reservation reservation) throws RARException {
		return reservation.getRental();
	}

	@Override
	public Reservation restoreRentalReservation(Rental rental) throws RARException {
		return rental.getReservation();
	}

	@Override
	public void deleteRentalReservation(Rental rental, Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeVehicleRental(Vehicle vehicle, Rental rental) throws RARException {
		vehicleManager.store(vehicle);
		rentalManager.store(rental);
	}

	@Override
	public List<Rental> restoreVehicleRental(Vehicle vehicle) throws RARException {
		return vehicle.getRentals();
	}

	@Override
	public Vehicle restoreVehicleRental(Rental rental) throws RARException {
		return rental.getVehicle();
	}

	@Override
	public void deleteVehicleRental(Vehicle vehicle, Rental rental) throws RARException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}

}
