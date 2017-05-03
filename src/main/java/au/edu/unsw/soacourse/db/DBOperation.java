package au.edu.unsw.soacourse.db;

import java.sql.*;

public class DBOperation
{
  public void addRecord(){
	  Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:jobs.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
	                   "VALUES (1, 'Paul', 32, 'California', 20000.00 );"; 
	      stmt.executeUpdate(sql);

	      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
	            "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );"; 
	      stmt.executeUpdate(sql);

	      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
	            "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );"; 
	      stmt.executeUpdate(sql);

	      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
	            "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );"; 
	      stmt.executeUpdate(sql);

	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records created successfully");
	  
  }
  
  public static void createJobDB(){
	  
	  Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:jobs.db");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE JOBS " +
	                   "(JOBID INT PRIMARY KEY   NOT NULL," +
	                   " COMPANY_NAME      TEXT," + 
	                   " SALARY_RATE       REAL," + 
	                   " POSITION_TYPE     TEXT," + 
	                   " LOCATION    	   TEXT," + 
	                   " DETAIL            TEXT," + 
	                   " STATUS            TEXT)"; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("jobTable created successfully");
	  }
  
 public static void createApplicationDB(){
	  
	  Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:application.db");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE APPLICATION " +
	                   "(APPID INT PRIMARY KEY   NOT NULL," +
	                   "JOBID   INT NOT NULL," +
	                   " DETAILS      TEXT," + 
	                   " COVERLETTER       REAL," + 
	                   " STATUS     TEXT,"+
	                   " FOREIGN KEY(JOBID)   REFERENCES JOBS(JOBID)"
	                   + ")" ; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("appTable created successfully");
	  }
 
 public static void reviewsDB(){
	  
	  Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:reviews.db");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE APPLICATION " +
	                   "(REVIEWID INT PRIMARY KEY   NOT NULL," +
	                   "APPID INT NOT NULL,"+
	                   " DETAILS      TEXT," + 
	                   " COMMENTS       TEXT," + 
	                   " DECISION     TEXT," +
	                   "FOREIGN KEY(APPID)   REFERENCES APPLICATION(APPID)"
	                   + ")"; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("reviewTable created successfully");
	  }
}