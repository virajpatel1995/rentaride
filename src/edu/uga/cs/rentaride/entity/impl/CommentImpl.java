package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class CommentImpl extends Persistence implements Comment {

	// Comment Attributes
		private String           text;
	    private Date			 Date;
	    private Rental			 rental;
	    private Customer		 customer;
	    private long			 id;
	    
	    public CommentImpl()
	    {
	        super( -1 );
	        this.text = null;
	        this.Date = null;
	        this.rental = null;
	        this.customer = null;
	        this.id = 0;
	    }

	    public CommentImpl( String text,
	                       Date date,
	                       Rental rental,
	                       Customer customer,
	                       long id
	                      )
	    {
	    	super( -1 );
	        this.text = text;
	        this.Date = date;
	        this.rental = rental;
	        this.customer = customer;
	        this.id = id;
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
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rental getRental() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

}
