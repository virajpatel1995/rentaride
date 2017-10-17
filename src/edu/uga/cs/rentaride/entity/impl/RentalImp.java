package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class RentalImp extends Persistence implements Rental {

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
	public void setPickupTime(Date pickupTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getReturnTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReturnTime(Date returnTime) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getLate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCharges() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCharges(int charges) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation getReservation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vehicle getVehicle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVehicle(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

}
