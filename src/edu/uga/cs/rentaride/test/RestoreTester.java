package edu.uga.cs.rentaride.test;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.impl.AdministratorImpl;
import edu.uga.cs.rentaride.entity.impl.CommentImpl;
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.DbUtils;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junwei on 10/23/2017.
 */
public class RestoreTester  {
    public static void main(String[] args) throws RARException {
        Connection conn = null;
        ObjectLayer objectLayer = null;
        PersistenceLayer persistence = null;

        // get a database connection
        try {
            conn = DbUtils.connect();
        } catch (Exception seq) {
            System.err.println("ReadTest: Unable to obtain a database connection");
        }

        if (conn == null) {
            System.out.println("ReadTest: failed to connect to the database");
            return;
        }

        // connect the ObjectModel module to the Persistence module
        objectLayer = new ObjectLayerImpl(persistence);
        // obtain a reference to Persistence module and connect it to the ObjectModel
        persistence = new PersistenceLayerImpl( conn, objectLayer );

//        Administrator administrator = new AdministratorImpl();
//        administrator.setId(2);
//
//        List<Administrator> administratorArrayList = persistence.restoreAdministrator(administrator);
//        printAdminList(administratorArrayList);

//        Comment comment = new CommentImpl();
//        comment.setText("Great carrrr");
//
//        List<Comment> comments = persistence.restoreComment(comment);
//        printCommentList(comments);

        Customer customer = new CustomerImpl();
        customer.setLastName("Dawson");

        List<Customer> customers = persistence.restoreCustomer(customer);
        printCustomerList(customers);


    }

    public static void printAdminList(List<Administrator> list) {
        System.out.println("Printing out admin list");
        for (Administrator admin: list) {
            System.out.println("ID: " + admin.getId());
            System.out.println("First Name: " + admin.getFirstName());
            System.out.println("Last Name: " + admin.getLastName());
            System.out.println("UserName: " + admin.getUserName());
            System.out.println("Email: " + admin.getEmail());
            System.out.println("Password: " + admin.getPassword());
            System.out.println("Created Date: " + admin.getCreatedDate());
            System.out.println("Address: " + admin.getAddress());
            System.out.println();
        }
    }
    public static void printCustomerList(List<Customer> list) {
        System.out.println("Printing out admin list");
        for (Customer customer: list) {
            System.out.println("ID: " + customer.getId());
            System.out.println("First Name: " + customer.getFirstName());
            System.out.println("Last Name: " + customer.getLastName());
            System.out.println("UserName: " + customer.getUserName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Password: " + customer.getPassword());
            System.out.println("Created Date: " + customer.getCreatedDate());
            System.out.println("Address: " + customer.getAddress());
            System.out.println();
        }
    }
    public static void printCommentList(List<Comment> list) {
        System.out.println("Printing out comment  list");
        for (Comment comment: list) {
            System.out.println("ID: " + comment.getId());
            System.out.println("Comment date: " + comment.getDate());
            System.out.println("Text: " + comment.getText());
            System.out.println("Rental Id: " + comment.getRental().getId());
            System.out.println();
        }
    }
}
