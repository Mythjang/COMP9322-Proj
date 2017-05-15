package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



public class DBOperationHireTeam
{
 

	
	
	public static void hireTeamDB(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:jobsManage.db");
			System.out.println("Opened database successfully");
	
			stmt = c.createStatement();
			String sql = "CREATE TABLE HIRETEAM " +
					"(hTEmail TEXT PRIMARY KEY   NOT NULL," +
					"jobId INT NOT NULL,"+
					"password      TEXT," + 
					"FOREIGN KEY(jobId)   REFERENCES JOBS(jobId)"
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