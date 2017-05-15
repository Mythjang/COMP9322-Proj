package au.edu.unsw.soacourse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import au.edu.unsw.soacourse.dao.PollList;
import au.edu.unsw.soacourse.model.Poll;



public class DBOperation {
	
	  public static void main( String args[] )
	  {
		  
	  }
		public static PollList getAllPolls() throws ClassNotFoundException, SQLException {
			Connection conn = DataSource.getConnection();
			Statement stmt = null;
			List<Poll> polls = new ArrayList<Poll>();
			System.out.println("Creating statement select all polls");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM polls;";
			ResultSet rs = stmt.executeQuery(sql);
			Poll poll;
			while(rs.next()){
					poll = new Poll();
					poll.setKey(rs.getString("pid"));
					poll.setEmail(rs.getString("email"));
					poll.setTitle(rs.getString("pollTitle"));
					poll.setDescription(rs.getString("description"));
					poll.setPollOptionType(rs.getString("pollOptionType"));
					poll.setOptions(rs.getString("options"));
					poll.setComments(rs.getString("comments"));
					poll.setFinalChoice(rs.getString("finalChoice"));
					polls.add(poll);
			}
			rs.close();
			stmt.close();
			conn.close();
			DataSource.returnConnection(conn);

			return new PollList(polls);
		}

		
	 
		public static Poll getPollById(String id) throws ClassNotFoundException, SQLException {
			Connection conn = DataSource.getConnection();
			Poll poll= new Poll();
			System.out.println("Creating statement select poll");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM polls where polls.pid = "+"'"+id+"'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			poll.setKey(rs.getString("pid"));
			poll.setEmail(rs.getString("email"));
			poll.setTitle(rs.getString("pollTitle"));
			poll.setDescription(rs.getString("description"));
			poll.setPollOptionType(rs.getString("pollOptionType"));
			poll.setOptions(rs.getString("options"));
			poll.setComments(rs.getString("comments"));
			poll.setFinalChoice(rs.getString("finalChoice"));
//			System.out.println(poll);
			rs.close();
			stmt.close();
			conn.close();
			DataSource.returnConnection(conn);
			return poll;
		}
	 

		public static String addPoll(String email, String pollTitle, String description, String pollOptionType, String options,
			String comments, String finalChoice) throws ClassNotFoundException, SQLException {
			Connection conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
		    UUID uuid = UUID.randomUUID();
		    String pid = uuid.toString();
		    String sql = "INSERT INTO polls (pid,email,pollTitle,description,pollOptionType,options,comments,finalChoice) " +
	                   "VALUES ('"+pid+"','"+email+"','"+pollTitle+"','"+description+"','"+pollOptionType+"','"+options+"','"+comments+"','"+finalChoice+"');"; 
		    System.out.println(sql);
		    stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			DataSource.returnConnection(conn);
			System.out.println("Records created successfully");
			return String.valueOf(pid);
		}
		
		
		
		public static void deletePollById(String id) throws ClassNotFoundException, SQLException {
			Connection conn = DataSource.getConnection();
		      System.out.println("Opened database successfully");
		      Statement stmt = conn.createStatement();
		      String sql = "delete from polls where pid="+"'"+id+"'" ;
		      System.out.println(sql);
		      stmt.executeUpdate(sql);
		      stmt.close();
		      conn.close();
		      DataSource.returnConnection(conn);
		}
		

		  public static void createPollsDB() throws ClassNotFoundException, SQLException{
			  Connection conn = DataSource.getConnection();
			  System.out.println("Opened database successfully");
			  Statement stmt = conn.createStatement();
			  String sql = "CREATE TABLE polls " +
			                   "(pid TEXT PRIMARY KEY   NOT NULL," +
			                   " email TEXT," +
			                   " pollTitle      TEXT," + 
			                   " description       TEXT," + 
			                   " pollOptionType     TEXT," + 
			                   " options    	   TEXT," + 
			                   " comments            TEXT," + 
			                   " finalChoice            TEXT);"; 
			  stmt.executeUpdate(sql);
			  stmt.close();
			  conn.close();
			  DataSource.returnConnection(conn);
			  System.out.println("PollsTable created successfully");
		  }
		  
		  public static void createVotesDB() throws ClassNotFoundException, SQLException{
			  Connection conn = DataSource.getConnection();
			  System.out.println("Opened database successfully");
			  Statement stmt = conn.createStatement();
			  String sql = "CREATE TABLE votes " +
			                   "(vid TEXT PRIMARY KEY   NOT NULL," +
			                   " pid   TEXT NOT NULL," +
			                   " email      TEXT," + 
			                   " chosenOption       TEXT," + 
			                   " FOREIGN KEY(pid)   REFERENCES polls(pid)"
			                   + ")" ; 
			  stmt.executeUpdate(sql);
			  stmt.close();
			  conn.close();
			  DataSource.returnConnection(conn);
			  System.out.println("VotesTable created successfully");
		  }
}
