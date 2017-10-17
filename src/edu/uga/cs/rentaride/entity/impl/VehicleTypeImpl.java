package edu.uga.cs.rentaride.entity.impl;

import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class VehicleTypeImpl extends Persistence implements VehicleType {

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
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HourlyPrice> getHourlyPrices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> getVehicles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservations() {
		// TODO Auto-generated method stub
		return null;
	}

}
