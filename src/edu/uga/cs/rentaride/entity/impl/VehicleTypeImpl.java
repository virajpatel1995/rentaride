package edu.uga.cs.rentaride.entity.impl;

import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class VehicleTypeImpl extends Persistence implements VehicleType {

	// VehicleType Attributes
			private String           name;
		    private long			 id;
		    
	public VehicleTypeImpl()
	{
		super( -1 );
		this.name = null;
		this.id = 0;
	}

	public VehicleTypeImpl( String name,long id)
	{
		super( -1 );
		this.name = name;
		this.id = id;
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}
//FINISH********************************************************************************
//	@Override
//	public boolean isPersistent() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//FINISH********************************************************************************

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) throws RARException {
		this.name = name;
	}

	//FINISH********************************************************************************
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
	//FINISH********************************************************************************
}
