package edu.uga.cs.rentaride.persistence.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;

enum type{
	Customer, Administrator
}//type

public class AdministratorManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public AdministratorManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Administrator administrator) throws RARException {
		String insertAdministratorSql = "insert into user ( type, firstName, lastName, userName, password, email, address, createdDate ) values ( ?, ?, ?, ?, ?, ?, ?, ? )";
		String updateAdministratorSql = "update person  set type = ? firstName = ?, lastName = ?, userName = ?, password = ?, email = ?, address = ?, createdDate = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long userId;
		
		try {
	
			if(!administrator.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertAdministratorSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateAdministratorSql);
		
			stmt.setObject(1,  "Administrator");
		
			if(administrator.getFirstName() != null)
					stmt.setString(2,administrator.getFirstName());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: FirstName undefined");

			if(administrator.getLastName() != null)
					stmt.setString(3,administrator.getLastName());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: LastName undefined");

			if(administrator.getUserName() != null)
					stmt.setString(4,administrator.getUserName());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: UserName undefined");

			if(administrator.getPassword() != null)
					stmt.setString(5,administrator.getPassword());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: Password undefined");

			if(administrator.getEmail() != null)
					stmt.setString(6,administrator.getEmail());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: Email undefined");

			if(administrator.getAddress() != null)
					stmt.setString(7,administrator.getAddress());
			else
					throw new RARException("AdministratorManager.save: can't save an Administrator: Address undefined");

			if(administrator.getCreatedDate() != null) {
                Object sqldate = new java.sql.Timestamp(administrator.getCreatedDate().getTime());
                stmt.setObject(8, sqldate);
            }else
					throw new RARException("AdministratorManager.save: can't save an Administrator: Created Date undefined");
			
			if(administrator.isPersistent())
				stmt.setLong(9,  administrator.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!administrator.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							userId = r.getLong(1);
							if(userId > 0)
								administrator.setId(userId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("AdministratorManager.save: failed to save an Administrator");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("AdministratorManager.save: Failed to save an Administrator: " + e);
		}//try catch
	
	}//store
	
	public List<Administrator> restore(Administrator administrator) throws RARException{
		String       selectAdminSql = "select id, type, firstName, lastName, userName, password, email, address, createdDate, memberUntil, licState, licNumber, ccNumber, ccExpiration, status from user";
		Statement    stmt = null;
		StringBuffer query = new StringBuffer( 100 );
		StringBuffer condition = new StringBuffer( 100 );
		List<Administrator> administrators = new ArrayList<>();

		condition.setLength( 0 );

		// form the query based on the given Person object instance
		query.append( selectAdminSql );
		if(administrator != null){
			if(administrator.getId() >= 0)
				query.append(" where id = " + administrator.getId());
			else if (administrator.getUserName() != null)
				query.append(" where username = '" + administrator.getUserName() + "'");
            else {
                    if( condition.length() > 0 )
                        condition.append( " and" );
                    condition.append( " type = '" + "Administrator" + "'" );

                    if( administrator.getPassword() != null )
                        condition.append( " password = '" + administrator.getPassword() + "'" );

					if( administrator.getEmail() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " email = '" + administrator.getEmail() + "'" );
					}

					if( administrator.getFirstName() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " firstName = '" + administrator.getFirstName() + "'" );
					}

					if( administrator.getLastName() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " lastName = '" + administrator.getLastName() + "'" );
					}

					if( administrator.getAddress() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " address = '" + administrator.getAddress() + "'" );
					}

					if( administrator.getCreatedDate() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " createdDate = '" + administrator.getCreatedDate() + "'" );
					}
					if( administrator.getUserStatus() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " status = '" + administrator.getUserStatus() + "'" );
					}
					if( condition.length() > 0 ) {
						query.append(  " where " );
						query.append( condition );
					}
				}
			}

			try {

				stmt = conn.createStatement();

				// retrieve the persistent Administrator objects
				//
				if( stmt.execute( query.toString() ) ) { // statement returned a result
					ResultSet rs = stmt.getResultSet();

					long id;
					String firstName;
					String lastName;
					String userName;
					String password;
					String email;
					String address;
					Date date;

					while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
						id = rs.getLong( 1 );
						firstName = rs.getString( 3 );
						lastName = rs.getString( 4 );
						userName = rs.getString( 5 );
						password = rs.getString( 6 );
						email = rs.getString( 7 );
						address = rs.getString( 8 );
						date = rs.getDate( 9 );

						Administrator administrator1 = objectLayer.createAdministrator( firstName, password, email, firstName, lastName, address, date);
						administrator1.setId( id );

						administrators.add( administrator1 );

					}

					return administrators;
				}
			}
			catch( Exception e ) {      // just in case...
				throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator objects" );
		}//restore
	
	
	
	
	public void delete(Administrator administrator) throws RARException{
		
		String deleteAdministratorSql = "delete from administrator where id = ?";              
		PreparedStatement stmt = null;
		int inscnt;
		             
		        if( !administrator.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteAdministratorSql );         
		            stmt.setLong( 1, administrator.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "AdministratorManager.delete: failed to delete a Administrator" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "AdministratorManager.delete: failed to delete a Administrator: " + e );       
		            }
		    }
		
		
			
	
}//AdministratorManager
