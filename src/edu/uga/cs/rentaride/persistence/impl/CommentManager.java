package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class CommentManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public CommentManager(Connection conn, ObjectLayer objectLayer) throws RARException{
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Comment comment) throws RARException{
		String insertCommentSql = "insert into comment ( comment, commentDate, rentalid ) values ( ?, ?, ? )";
		String updateCommentSql = "update person  set comment = ?, commentDate = ?, rentalid = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long commentId;
		
		if(comment.getRental() == null)
			throw new RARException ("CommentManager.save: Attempting ot save a Comment with no Rental defined");
		if(!comment.getRental().isPersistent())	
			throw new RARException ("CommentManager.save: Attempting ot save a Comment with no Rental is not persistent");
		
		try {
	
			if(!comment.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertCommentSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateCommentSql);
		
			if(comment.getText() != null)
				stmt.setString(1,comment.getText());
			else
				throw new RARException("CommentManager.save: can't save an Comment: Text undefined");

			if(comment.getDate() != null)
				stmt.setDate(2,new java.sql.Date(comment.getDate().getTime()));
			else
				stmt.setNull(2,  java.sql.Types.DATE);
			
			stmt.setLong(3,  comment.getRental().getId());
			
			if(comment.isPersistent())
				stmt.setLong(4,  comment.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!comment.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							commentId = r.getLong(1);
							if(commentId > 0)
								comment.setId(commentId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("CommentManager.save: failed to save a comment");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("CommentManager.save: Failed to save a comment: " + e);
		}//try catch
	}//store
	
	public List<Comment> restore(Comment comment) throws RARException{
		{
			String       selectCommentSql = "select id, type, firstName, lastName, userName, password, email, address, createdDate, memberUntil, licState, licNumber, ccNumber, ccExpiration, status";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<Comment> comments = new ArrayList<>();

			condition.setLength( 0 );

			// form the query based on the given Person object instance
//			query.append( selectCommentSql );
//			if(comment != null){
//				if(comment.getId() >= 0)
//					query.append(" where id = " + comment.getId());
//				else if (comment.getUserName() != null)
//					query.append(" where username = '" + comment.getUserName() + "'");
//				else {
//					if( comment.getPassword() != null )
//						condition.append( " password = '" + comment.getPassword() + "'" );
//
//					if( comment.getEmail() != null ) {
//						if( condition.length() > 0 )
//							condition.append( " and" );
//						condition.append( " email = '" + comment.getEmail() + "'" );
//					}
//
//					if( comment.getFirstName() != null ) {
//						if( condition.length() > 0 )
//							condition.append( " and" );
//						condition.append( " firstName = '" + comment.getFirstName() + "'" );
//					}
//
//					if( comment.getLastName() != null ) {
//						if( condition.length() > 0 )
//							condition.append( " and" );
//						condition.append( " lastName = '" + comment.getLastName() + "'" );
//					}
//
//					if( comment.getAddress() != null ) {
//						if( condition.length() > 0 )
//							condition.append( " and" );
//						condition.append( " address = '" + comment.getAddress() + "'" );
//					}
//
//					if( comment.getCreatedDate() != null ) {
//						if( condition.length() > 0 )
//							condition.append( " and" );
//						condition.append( " createdDate = '" + comment.getCreatedDate() + "'" );
//					}
//					if( comment.getUserStatus() != null ) {
//						if( condition.length() > 0 )
//							condition.append( " and" );
//						condition.append( " status = '" + comment.getUserStatus() + "'" );
//					}
//					if( condition.length() > 0 ) {
//						query.append(  " where " );
//						query.append( condition );
//					}
//				}
//			}
//
//			try {
//
//				stmt = conn.createStatement();
//
//				// retrieve the persistent Administrator objects
//				//
//				if( stmt.execute( query.toString() ) ) { // statement returned a result
//					ResultSet rs = stmt.getResultSet();
//
//					long id;
//					String firstName;
//					String lastName;
//					String userName;
//					String password;
//					String email;
//					String address;
//					Date date;
//
//					while( rs.next() ) {
///**
// *  columnIndex need to match column index in database
// */
//						id = rs.getLong( 1 );
//						firstName = rs.getString( 2 );
//						lastName = rs.getString( 3 );
//						userName = rs.getString( 4 );
//						password = rs.getString( 5 );
//						email = rs.getString( 6 );
//						address = rs.getString( 7 );
//						date = rs.getDate( 8 );
//
//						Administrator administrator1 = objectLayer.createAdministrator( firstName, password, email, firstName, lastName, address, date);
//						administrator1.setId( id );
//
//						administrators.add( administrator1 );
//
//					}
//
//					return administrators;
//				}
//			}
//			catch( Exception e ) {      // just in case...
//				throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator object; Root cause: " + e );
//			}

			// if we get to this point, it's an error
			throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator objects" );
		}}//restore
	
	public void delete(Comment comment) throws RARException{
		
		String deleteCommentSql = "delete from comment where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !comment.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSql );         
		            stmt.setLong( 1, comment.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "CommentManager.delete: failed to delete a Comment" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "CommentManager.delete: failed to delete a Comment: " + e );       
		            }
		
		
		
		
		
		
	}//delete
	
}//CommentManager