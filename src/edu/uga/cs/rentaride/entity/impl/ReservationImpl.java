package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class ReservationImpl extends Persistence implements Reservation {

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date getPickupTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPickupTime(Date pickupTime) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLength(int length) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomer(Customer customer) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleType getVehicleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RentalLocation getRentalLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rental getRental() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRental(Rental rental) {
		// TODO Auto-generated method stub
		
	}

}
