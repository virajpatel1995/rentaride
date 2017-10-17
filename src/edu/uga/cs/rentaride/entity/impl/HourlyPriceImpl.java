package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class HourlyPriceImpl extends Persistence implements HourlyPrice{

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
	public int getMaxHours() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxHours(int maxHours) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPrice(int price) throws RARException {
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

}
