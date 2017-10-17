package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class RentARideParamsImpl extends Persistence implements RentARideParams{

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
	public int getMembershipPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMembershipPrice(int membershipPrice) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLateFee() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLateFee(int lateFee) throws RARException {
		// TODO Auto-generated method stub
		
	}

}
