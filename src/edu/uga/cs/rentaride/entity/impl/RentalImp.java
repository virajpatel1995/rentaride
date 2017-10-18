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

	private long id;
	private Date pickupTime;
	private Date returnTime;
	private boolean late;
	private int charges;
	private Reservation reservation;
	private Vehicle vehicle;
	private Customer customer;
	private Comment comment;



	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
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
		return pickupTime;
	}

	@Override
	public void setPickupTime(Date pickupTime) {
	    this.pickupTime = pickupTime;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getReturnTime() {
		// TODO Auto-generated method stub
		return returnTime;
	}

	@Override
	public void setReturnTime(Date returnTime) throws RARException {
	    this.returnTime = returnTime;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getLate() {
		// TODO Auto-generated method stub
		return late;
	}

	@Override
	public int getCharges() {
		// TODO Auto-generated method stub
		return charges;
	}

	@Override
	public void setCharges(int charges) throws RARException {
	    this.charges = charges;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation getReservation() {
		// TODO Auto-generated method stub
		return reservation;
	}

	@Override
	public void setReservation(Reservation reservation) throws RARException {
	    this.reservation = reservation;
		// TODO Auto-generated method stub

	}

	@Override
	public Vehicle getVehicle() {
		// TODO Auto-generated method stub
		return vehicle;
	}

	@Override
	public void setVehicle(Vehicle vehicle) throws RARException {
	    this.vehicle = vehicle;
		// TODO Auto-generated method stub

	}

	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return customer;
	}

	@Override
	public Comment getComment() {
		// TODO Auto-generated method stub
		return comment;
	}

	@Override
	public void setComment(Comment comment) {
	    this.comment = comment;
		// TODO Auto-generated method stub
		
	}

}
