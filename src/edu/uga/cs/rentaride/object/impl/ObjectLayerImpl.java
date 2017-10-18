package edu.uga.cs.rentaride.object.impl;

import java.util.Date;
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
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.Persistence;
import edu.uga.cs.rentaride.entity.impl.AdministratorImpl;
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.entity.impl.RentalImp;
import edu.uga.cs.rentaride.entity.impl.RentalLocationImpl;
import edu.uga.cs.rentaride.entity.impl.ReservationImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleTypeImpl;



public class ObjectLayerImpl implements ObjectLayer {
	
	PersistenceLayer persistence = null;

	
	public ObjectLayerImpl(){
        this.persistence = null;
        System.out.println( "ObjectLayerImpl.ObjectLayerImpl(): initialized" );
    }
	
	public ObjectLayerImpl( PersistenceLayer persistence ){
        this.persistence = persistence;
        System.out.println( "ObjectLayerImpl.ObjectLayerImpl(persistence): initialized" );
    }
	
	@Override
	public Administrator createAdministrator() {
		AdministratorImpl Administrator = new AdministratorImpl(null, null, null, null, null,null, null, null, 0);
		 Administrator.setId(-1);
		 Persistence.setPersistencvalayer(persistence);
		 return Administrator;
		
	}


	
	@Override
	public Administrator createAdministrator(String firstName, String lastName, String userName, String password,
			String email, String address, Date createDate) throws RARException {
		// TODO Auto-generated method stub
		AdministratorImpl Administrator = new AdministratorImpl(firstName, lastName, userName, password, email, address, createDate, null, 0);
		Persistence.setPersistencvalayer(persistence);
		return Administrator;
			}

	
	@Override
	public List<Administrator> findAdministrator(Administrator modelAdministrator) throws RARException {
		
		return persistence.restoreAdministrator(modelAdministrator);
	}

	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		
		persistence.storeAdministrator(administrator);		
	}

	@Override
	public void deleteAdministrator(Administrator administrator) throws RARException {
		persistence.deleteAdministrator(administrator);
		
	}
	
	@Override
	public Customer createCustomer() {
		// TODO Auto-generated method stub
		CustomerImpl Customer = new CustomerImpl(null, null, null, null, null, null, null, null, null, null, null, null);
		Customer.setId(-1);
		Persistence.setPersistencvalayer(persistence);
		return Customer;
	}

	@Override
	public Customer createCustomer(String firstName, String lastName, String userName, String password, String email,
			String address, Date createDate, Date membershipExpiration, String licenseState, String licenseNumber,
			String cardNumber, Date cardExpiration) throws RARException {
		
		CustomerImpl Customer = new CustomerImpl(userName, password, email, firstName, lastName, address, licenseState, licenseNumber, cardNumber,createDate, membershipExpiration, cardExpiration);
		Persistence.setPersistencvalayer(persistence);
		return Customer;
		
	}

	
	@Override
	public List<Customer> findCustomer(Customer modelCustomer) throws RARException {
		return persistence.restoreCustomer(modelCustomer);
	}

	@Override
	public void storeCustomer(Customer customer) throws RARException {
		persistence.storeCustomer(customer);
		
	}

	@Override
	public RentalLocation createRentalLocation(String name, String address, int capacity) throws RARException {
		
		RentalLocationImpl RentalLocation = new RentalLocationImpl(name, address, capacity);
		Persistence.setPersistencvalayer(persistence);
		return RentalLocation;
	}

	@Override
	public RentalLocation createRentalLocation() {
		RentalLocationImpl RentalLocation = new RentalLocationImpl(null, null, 0);
		Persistence.setPersistencvalayer(persistence);
		return RentalLocation;
	}

	@Override
	public List<RentalLocation> findRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return persistence.restoreRentalLocation(modelRentalLocation);
	}

	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.storeRentalLocation(rentalLocation);
	}

	@Override
	public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.deleteRentalLocation(rentalLocation);
	}

	@Override
	public Reservation createReservation(Date pickupTime, int rentalLength, VehicleType vehicleType,
			RentalLocation rentalLocation, Customer customer) throws RARException {
		
		ReservationImpl Reservation = new ReservationImpl(pickupTime, rentalLength, customer, vehicleType, rentalLocation, null);
		Persistence.setPersistencvalayer(persistence);
		return Reservation;
	}

	@Override
	public Reservation createReservation() {
		ReservationImpl Reservation = new ReservationImpl();
		Persistence.setPersistencvalayer(persistence);
		return Reservation;
	}

	@Override
	public List<Reservation> findReservation(Reservation modelReservation) throws RARException {
		return  persistence.restoreReservation(modelReservation);
	}

	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		 persistence.restoreReservation(reservation);
		
	}

	@Override
	public void deleteReservation(Reservation reservation) throws RARException {
		 persistence.deleteReservation(reservation);
		
	}

	@Override
	public Rental createRental(Date pickupTime, Reservation reservation, Vehicle vehicle) throws RARException {
		RentalImp Rental = new RentalImp(pickupTime, pickupTime, false, 0, reservation, vehicle, null, null);
		Persistence.setPersistencvalayer(persistence);
		return Rental;
	}

	@Override
	public Rental createRental() {
		RentalImp Rental = new RentalImp();
		Persistence.setPersistencvalayer(persistence);
		return Rental;
	}

	@Override
	public List<Rental> findRental(Rental modelRental) throws RARException {
		
		return  persistence.restoreRental(modelRental);

	}

	@Override
	public void storeRental(Rental rental) throws RARException {
		  persistence.restoreRental(rental);
	
	}

	@Override
	public void deleteRental(Rental rental) throws RARException {
		  persistence.deleteRental(rental);

		
	}

	@Override
	public VehicleType createVehicleType(String name) throws RARException {
		VehicleTypeImpl VehicleType = new VehicleTypeImpl(name);
		Persistence.setPersistencvalayer(persistence);
		return VehicleType;
	}

	@Override
	public VehicleType createVehicleType() {
		VehicleTypeImpl VehicleType = new VehicleTypeImpl();
		Persistence.setPersistencvalayer(persistence);
		return VehicleType;
	}

	@Override
	public List<VehicleType> findVehicleType(VehicleType modelVehicleType) throws RARException {
		// TODO Auto-generated method stub
		return persistence.restoreVehicleType(modelVehicleType);

	}

	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		  persistence.restoreVehicleType(vehicleType);
		
	}

	@Override
	public void deleteVehicleType(VehicleType vehicleType) throws RARException {
		  persistence.deleteVehicleType(vehicleType);

	}

	@Override
	public Vehicle createVehicle(String make, String model, int year, String registrationTag, int mileage,
			Date lastServiced, VehicleType vehicleType, RentalLocation rentalLocation,
			VehicleCondition vehicleCondition, VehicleStatus vehicleStatus) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle createVehicle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> findVehicle(Vehicle modelVehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVehicle(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment createComment(String text, Date date, Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment createComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findComment(Comment modelComment) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeComment(Comment comment) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(Comment comment) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HourlyPrice createHourlyPrice(int maxHrs, int price, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HourlyPrice createHourlyPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HourlyPrice> findHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RentARideParams createRentARideParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RentARideParams findRentARideParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeRentARideParams(RentARideParams rentARideParams) throws RARException {
		// TODO Auto-generated method stub
		
	}

}
