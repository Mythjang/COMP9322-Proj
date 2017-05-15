package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Application;
import model.User;

public class DBOperation{
 

	
	public static boolean addUser(User user) throws SQLException, ClassNotFoundException {
	 	Connection conn = DataSource.getConnection();
		System.out.println("Opened database successfully");
		Statement stmt = conn.createStatement();
		try{
		String sql = "INSERT INTO USERS (email,type,name,title,dob,password,workFor,currentPosition,pastExperience,professionalSkills,education) " +
				"VALUES ('"+user.getEmail()+"','"+user.getType()+"','"+ user.getName()+"','"+user.getTitle()+"','"+user.getDob()+"','"+user.getPassword()+"','"+user.getWorkFor()+"','"+user.getCurrentPosition()+"','"+user.getPastExperience()+"','"+user.getProfessionalSkills()+"','"+user.getEducation()+"');"; 
		System.out.println(sql);
		stmt.executeUpdate(sql);
		}
		catch(SQLException e){
			stmt.close();
			conn.close();
			DataSource.returnConnection(conn);
			return false;
		}
		stmt.close();
		conn.close();
		DataSource.returnConnection(conn);
	    System.out.println("User created successfully");
	    return true;
	}

	
	public static String checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
		Connection conn = DataSource.getConnection();
		System.out.println("Opened database successfully");
		Statement stmt = conn.createStatement();
		String sql = "Select password, type from USERS where email="+"'"+email+"';" ;
		System.out.println(sql);
		String type = null;
		try{
			ResultSet rs = stmt.executeQuery(sql); 
			String correctPass = rs.getString("password");
			type = rs.getString("type");
			rs.close();
			if (password.equals(correctPass)) return type;
		}catch(SQLException e){
			
			
		}finally{
			stmt.close();
			conn.close();
			DataSource.returnConnection(conn);
		}
		System.out.println("login check failed");
		return null;
	}
	
	
	
	
	public static User getUserDetail(String email) throws SQLException, ClassNotFoundException {
		Connection conn = DataSource.getConnection();
		System.out.println("Opened database successfully");
		Statement stmt = conn.createStatement();
		String sql = "Select * from USERS where email="+"'"+email+"';" ;
		System.out.println(sql);
		User user = new User();
		user.setEmail(email);
		ResultSet rs = stmt.executeQuery(sql); 
		user.setName(rs.getString("name"));
		user.setTitle(rs.getString("title"));
		user.setType(rs.getString("type"));
		user.setDob(rs.getString("dob"));
		user.setCurrentPosition(rs.getString("currentPosition"));
		user.setEducation(rs.getString("education"));
		user.setPastExperience(rs.getString("pastExperience"));
		user.setProfessionalSkills(rs.getString("professionalSkills"));
		rs.close();
		stmt.close();
		conn.close();
		DataSource.returnConnection(conn);
		return user;
	}
	
	

	public static List<User> getHireTeam(String user) throws SQLException, ClassNotFoundException {
	    Connection conn = DataSource.getConnection();
		System.out.println("Creating statement select users");
		Statement stmt = conn.createStatement();
		List<User> hireTeams = new ArrayList<User>();
		String sql = "SELECT email, password FROM USERS where workFor="+"'"+user+"';";
		ResultSet rs = stmt.executeQuery(sql);
		User hireTeam ;
		while(rs.next()){
			hireTeam = new User();
			//Retrieve by column name
			hireTeam.setEmail(rs.getString("email"));
			hireTeam.setPassword(rs.getString("password"));

			hireTeams.add(hireTeam);	
		}
		rs.close();
		stmt.close();
		conn.close();
		DataSource.returnConnection(conn);
		if(hireTeams.size() == 0) return null;
		return hireTeams;
			
		
	}
	

	public static void createUserDB() throws SQLException, ClassNotFoundException{
		Connection conn = DataSource.getConnection();
		System.out.println("Opened database successfully");
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE USERS" +
				"(email TEXT PRIMARY KEY   NOT NULL," +
				" type			 	TEXT," +
				" password		 	TEXT," +
				" name     			TEXT," + 
				" title     		TEXT," + 
				" dob     			TEXT," + 
				" workFor    		TEXT," + 
				" currentPosition	TEXT," +
				" pastExperience    TEXT," + 
				" professionalSkills TEXT," +
				" education      	TEXT);";
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
		DataSource.returnConnection(conn);
	    System.out.println("User table created successfully");
	}


	public static void updateUserDetail(String email, User user) throws ClassNotFoundException, SQLException {
		System.out.println(user.toString());
		Connection conn =  DataSource.getConnection();
	    System.out.println("Opened database successfully");
	    Statement stmt = conn.createStatement();
	    String sql = "UPDATE USERS" +
	              " set pastExperience="+"'"+user.getPastExperience()+"'"+
	              ", professionalSkills="+"'"+user.getProfessionalSkills()+"'"+", education="+"'"+user.getEducation()+"'"+", currentPosition="+"'"+user.getCurrentPosition()+"'"+
	              " where email="+"'"+email+"'";
	    System.out.println(sql);
	    stmt.executeUpdate(sql);
	    stmt.close();
	    conn.close();
	    DataSource.returnConnection(conn);
	}


		
}