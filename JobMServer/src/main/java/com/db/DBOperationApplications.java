package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBOperationApplications {

	
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
	 
}
