package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

import java.util.Date;

public class RentalLocationImpl extends Persistence implements RentalLocation {

    private String name;
    private String address;
    private int capacity;

    public RentalLocationImpl(String name, String address, int capacity) {
        super(-1);
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }
    public RentalLocationImpl(){
        super(-1);
        this.name = null;
        this.address = null;
        this.capacity = 0;
    }

    @Override
    public void setName(String name) throws RARException{
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) throws RARException{
        this.capacity = capacity;
    }

    @Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return name;
	}


}
