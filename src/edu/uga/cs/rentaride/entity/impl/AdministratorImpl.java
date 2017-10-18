package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.Persistence;


public class AdministratorImpl extends Persistence implements Administrator {

	// Administrator Attributes
	private String           userName;
    private String           password;
    private String           email;
    private String           firstName;
    private String           lastName;
    private String           address;
    private Date			 createDate;
    private UserStatus		 userStatus;
    private long			 id;
    
    public AdministratorImpl()
    {
        super( -1 );
        this.userName = null;
        this.password = null;
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.address = null;
        this.createDate = null;
        this.userStatus = null;
        this.id = 0;
    }

    public AdministratorImpl( String userName,
                       String password,
                       String email,
                       String firstName,
                       String lastName,
                       String address,
                       Date createDate,
                       UserStatus userStatus,
                       long id)
    {
        super( -1 );
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.createDate = createDate;
        this.userStatus = userStatus;
        this.id = id;
    }
	
	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) throws RARException {
		this.userName = userName;
		
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Date getCreatedDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;		
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
	public UserStatus getUserStatus() {
		return userStatus;
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		return false;
	}

}
