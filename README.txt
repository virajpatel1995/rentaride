# rentaride

PART 6

TEAM 10
Shepherd Ogden, Viraj Patel, Jacob Ambrose, Junwei Ren
________________________

This is the main directory of our Rent A Ride project.

The directory structure is as follows:

  src           contains all sources of the system;
                all sources are in a few sub-packages of edu.uga.clubs
                and include:

                edu/uga/cs/rentaride/entity         		includes the entity classes (data model)
                edu/uga/cs/rentaride/entity.impl    		includes the entity implementation
                edu/uga/cs/rentaride/object					includes the object model layer subystem
                edu/uga/cs/rentaride/object.impl			includes the object model layer implementation
                edu/uga/cs/rentaride/persistence			includes the persistence model layer
                edu/uga/cs/rentaride/persistence.impl		includes the persistence model layer implementation
                edu/uga/cs/rentaride/test            		includes our test programs
                edu/uga/cs/rentaride/RARException.java  	the main exception class

  build			Contains .gitignore
  
  docs			Contains our guidelines for the project
  
  scripts       Contains the scripts for the DB database schema

  lib           includes the necessary jar files (libraries); these
                are used only by the build process and are not
                deployed as part of the Web application.  The
                libraries which are deployed as part of the Web
                application are in the directory WebContent/WEB-INF/lib.

  WebContent    directory for the assembly of the Web Application

  RentARide-test.sh    a Shell script to compile the project, and also test the program  

To run and test the program:

   A simple script to compile and execute our test program is called
   RentARide-test.sh.   Execute:

   $ sh RentARide-test.sh