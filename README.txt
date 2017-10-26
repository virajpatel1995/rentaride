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

2.  Experimenting with the Object and Persistence layers

The instructions are for a Unix-based system (or Mac OS X).  To deploy
on MS Windows, you would need to modify some steps.

You will not need to create a WAR file with the Clubs Web application,
or deploy it to an application server.  However, you will need to
compile the whole system, including the test programs.

Here are the steps to perform:

0. Install the Java JDK and the MySQL database.
   If you'd like to experiment on uml, you do not need to
   install these systems, as they are already available on uml.
1. Create a database and install the schema using the SQL script in
   the db directory.
2. Compile the system using the compile.sh script
   You may need to modify the compile.sh script to change the setting
   of the CLASSESDIR variable to point to a directoy where you have the
   necessary JAR files (perhaps newer jars).
   Execute:

   $ sh compile.sh

3. Run the test programs
   A simple script to execute one of the test programs is called
   run-read-test.sh.   Execute:

   $ sh run-read-test.sh