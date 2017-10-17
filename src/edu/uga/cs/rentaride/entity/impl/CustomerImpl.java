package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class CustomerImpl extends Persistence implements Customer {

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserName(String userName) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreateDate(Date createDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAddress(String address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserStatus getUserStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {
		// TODO Auto-generated method stub
		
	}

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
	public Date getMemberUntil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMemberUntil(Date memberUntil) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLicenseState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLicenseState(String state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLicenseNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCreditCardNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreditCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getCreditCardExpiration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreditCardExpiration(Date cardExpiration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reservation> getReservations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rental> getRentals() {
		// TODO Auto-generated method stub
		return null;
	}

}
