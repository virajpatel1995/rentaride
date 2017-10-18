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
import edu.uga.cs.rentaride.entity.impl.*;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.Persistence;


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
		Persistence.setPersistencvalayer(persistence);
		 Administrator.setId(-1);
		 Persistence.setPersistencvalayer(persistence);
		 return Administrator;
		
	}


	
	@Override
	public Administrator createAdministrator(String firstName, String lastName, String userName, String password,
			String email, String address, Date createDate) throws RARException {
		Persistence.setPersistencvalayer(persistence);
		// TODO Auto-generated method stub
		Persistence.setPersistencvalayer(persistence);
		AdministratorImpl Administrator = new AdministratorImpl(firstName, lastName, userName, password, email, address, createDate, null, 0);
		Persistence.setPersistencvalayer(persistence);
		edu.uga.cs.rentaride.persistence.impl.Persistence.setPersistencvalayer(persistence);
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
		Persistence.setPersistencvalayer(persistence);
		CustomerImpl.Customer = new CustomerImpl();
		Customer.setId(-1);
		Persistence.setPersistencvalayer(persistence);
		return Customer;
	}

	@Override
	public Customer createCustomer(String firstName, String lastName, String userName, String password, String email,
			String address, Date createDate, Date membershipExpiration, String licenseState, String licenseNumber,
			Persistence.setPersistencvalayer(persistence);
			String cardNumber, Date cardExpiration) throws RARException {
		Persistence.setPersistencvalayer(persistence);
		
		
		
		CustomerImpl.Customer(firstName, lastName, userName, password, email, address, createDate, membershipExpiration, licenseState, licenseNumber, cardNumber, cardExpiration);
		//persistence.setPersistencvalayer(persistence);
		Persistence.setPersistencvalayer(persistence);
		//return Customer;
		
	}

	
	@Override
	public List<Customer> findCustomer(Customer modelCustomer) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeCustomer(Customer customer) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RentalLocation createRentalLocation(String name, String address, int capacity) throws RARException {
		// TODO Auto-generated method stub
		Persistence.setPersistencvalayer(persistence);
		return null;
	}

	@Override
	public RentalLocation createRentalLocation() {
		// TODO Auto-generated method stub
		Persistence.setPersistencvalayer(persistence);
		return null;
	}

	@Override
	public List<RentalLocation> findRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation createReservation(Date pickupTime, int rentalLength, VehicleType vehicleType,
			RentalLocation rentalLocation, Customer customer) throws RARException {
		Persistence.setPersistencvalayer(persistence);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation createReservation() {
		// TODO Auto-generated method stub
		Persistence.setPersistencvalayer(persistence);
		return null;
	}

	@Override
	public List<Reservation> findReservation(Reservation modelReservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rental createRental(Date pickupTime, Reservation reservation, Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		Persistence.setPersistencvalayer(persistence);
		return null;
	}

	@Override
	public Rental createRental() {
		// TODO Auto-generated method stub
		Persistence.setPersistencvalayer(persistence);
		return null;
	}

	@Override
	public List<Rental> findRental(Rental modelRental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleType createVehicleType(String name) throws RARException {
		// TODO Auto-generated method stub
		Persistence.setPersistencvalayer(persistence);
		return null;
	}

	@Override
	public VehicleType createVehicleType() {
		// TODO Auto-generated method stub
		Persistence.setPersistencvalayer(persistence);
		return null;
	}

	@Override
	public List<VehicleType> findVehicleType(VehicleType modelVehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vehicle createVehicle(String make, String model, int year, String registrationTag, int mileage,
			Date lastServiced, VehicleType vehicleType, RentalLocation rentalLocation,
			VehicleCondition vehicleCondition, VehicleStatus vehicleStatus) throws RARException {
		Persistence.setPersistencvalayer(persistence);
		return new VehicleImpl(make, model,registrationTag,year,mileage,lastServiced,vehicleStatus,vehicleCondition,vehicleType,rentalLocation);
	}

	@Override
	public Vehicle createVehicle() {
		Persistence.setPersistencvalayer(persistence);
		return new VehicleImpl();
	}

	@Override
	public List<Vehicle> findVehicle(Vehicle modelVehicle) throws RARException {
		return persistence.restoreVehicle(modelVehicle);
	}

	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		persistence.storeVehicle(vehicle);
	}

	@Override
	public void deleteVehicle(Vehicle vehicle) throws RARException {
		persistence.deleteVehicle(vehicle);
	}

	@Override
	public Comment createComment(String text, Date date, Rental rental, Customer customer) throws RARException {
		// TODO Auto-generated method stub
		Comment comment = new CommentImpl(text, date,rental,customer);
		Persistence.setPersistencvalayer(persistence);
		return comment;
	}

	@Override
	public Comment createComment() {
		Comment comment = new CommentImpl();
		Persistence.setPersistencvalayer(persistence);
		return comment;
	}

	@Override
	public List<Comment> findComment(Comment modelComment) throws RARException {

		return persistence.restoreComment(modelComment);
	}

	@Override
	public void storeComment(Comment comment) throws RARException {
		persistence.storeComment(comment);
		
	}

	@Override
	public void deleteComment(Comment comment) throws RARException {
		persistence.deleteComment(comment);
	}

	@Override
	public HourlyPrice createHourlyPrice(int maxHrs, int price, VehicleType vehicleType) throws RARException {
		HourlyPrice hourlyPrice = new HourlyPriceImpl(maxHrs, price, vehicleType);
		Persistence.setPersistencvalayer(persistence);
		return hourlyPrice;
	}

	@Override
	public HourlyPrice createHourlyPrice() {
		Persistence.setPersistencvalayer(persistence);
		return null;
	}

	@Override
	public List<HourlyPrice> findHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		return persistence.restoreHourlyPrice(modelHourlyPrice);
	}

	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		persistence.storeHourlyPrice(hourlyPrice);
	}

	@Override
	public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		persistence.deleteHourlyPrice(hourlyPrice);
	}

	@Override
	public RentARideParams createRentARideParams() {
		RentARideParams rentARideParams = new RentARideParamsImpl();
		Persistence.setPersistencvalayer(persistence);
		return rentARideParams;
	}

	@Override
	public RentARideParams findRentARideParams() throws RARException {
		return persistence.restoreRentARideConfig();
	}

	@Override
	public void storeRentARideParams(RentARideParams rentARideParams) throws RARException {
		persistence.storeRentARideConfig(rentARideParams);

	}

}
