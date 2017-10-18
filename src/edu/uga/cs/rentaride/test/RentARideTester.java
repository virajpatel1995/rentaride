package edu.uga.cs.rentaride.test;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

public class RentARideTester {

	public static void main(String[] args) {
		Connection  conn = null;
        ObjectLayer objectLayer = null;
        PersistenceLayer persistence = null;

        // get a database connection                                                                     
        try {
            conn = DbUtils.connect();
        }
        catch(Exception seq) {
            System.err.println( "ReadTest: Unable to obtain a database connection" );
        }

        if( conn == null ) {
            System.out.println( "ReadTest: failed to connect to the database" );
            return;
        }

        // obtain a reference to the ObjectModel module                                                  
        objectLayer = new ObjectLayerImpl();
        // obtain a reference to Persistence module and connect it to the ObjectModel                    
        persistence = new PersistenceLayerImpl( conn, objectLayer );
        // connect the ObjectModel module to the Persistence module                                      
        objectLayer.setPersistence( persistence );

	}

}
