package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import model.User;

public class DBOperation
{
 

	public static boolean addUser(User user) {
	 	Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:users.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      stmt = c.createStatement();
	      String sql = "INSERT INTO USERS (email,type,name,sex,dob,companyName,password,introduction) " +
                   "VALUES ('"+user.getEmail()+"','"+user.getType()+"','"+ user.getName()+"','"+user.getSex()+"','"+user.getDob()+"','"+user.getCompanyName()+"','"+user.getPassword()+"','"+user.getIntroduction()+ "');"; 
	      System.out.println(sql);
	      stmt.executeUpdate(sql);

	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	    System.out.println("User created successfully");
	    return true;
	}

	
	public static boolean checkLogin(String email, String password) {
	 	Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:users.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      stmt = c.createStatement();
	      String sql = "Select password from USERS where email="+"'"+email+"'" ;
	      System.out.println(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      String correctPass = rs.getString("password");
	      if (password.equals(correctPass)) return true;
	      System.out.println(sql);
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	    System.out.println("login check successfully");
		return false;
	}
	

	public static void createUserDB(){
		Connection c = null;
		Statement stmt = null;
	    try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:users.db");
			System.out.println("Opened database successfully");
		
			stmt = c.createStatement();
			String sql = "CREATE TABLE USERS" +
					"(email TEXT PRIMARY KEY   NOT NULL," +
					" type			 	TEXT,"+
					" name     			TEXT," + 
					" sex     			TEXT," + 
					" dob     			TEXT," + 
					" companyName		TEXT,"+
					" password       	TEXT," + 
					" introduction      TEXT)" +
					" jobId      		TEXT)"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );

		}
	    System.out.println("Usertable created successfully");
	}
  
		
}