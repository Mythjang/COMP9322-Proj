package com.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.Response.Status;

import com.customException.CustomException;
import com.dao.ReviewList;
import com.model.Link;
import com.model.Review;



public class DBOperationReview{
 
	public static void DBOperationReviewDB() throws ClassNotFoundException, SQLException{
		
		Connection conn = DataSource.getConnection();
		System.out.println("Opened database successfully");
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE REVIEWS " +
				"(reKey        TEXT PRIMARY KEY   NOT NULL," +
				"appKey        TEXT NOT NULL,"+
				"reviewerEmail TEXT," + 
				"decision      TEXT," + 
				"comments      TEXT," + 
				"FOREIGN KEY(appKey)   REFERENCES APPS(appKey)"
				+ ")"; 
		stmt.executeUpdate(sql);
		stmt.close();
		//conn.close();
		DataSource.returnConnection(conn);
	    System.out.println("revTable created successfully");
	}
	
	public static String createNewReview(String appKey,String reviewerEmail, String decision, String comments) throws ClassNotFoundException, SQLException, CustomException {
			DBOperationApplications.getAppByAppKey(appKey);
			if(DBOperationReview.isReviewd(appKey,reviewerEmail)) throw new CustomException(Status.BAD_REQUEST,"this application has already been reivewed by "+reviewerEmail);
			Connection conn = DataSource.getConnection();
			String reKey = UUID.randomUUID().toString();
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO REVIEWS (reKey,appKey,reviewerEmail,decision,comments) " +
			"VALUES ('"+reKey+"','"+appKey+"','"+reviewerEmail+"','"+decision+"','"+comments+ "');"; 
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			//conn.close();
			DataSource.returnConnection(conn);
			System.out.println("review created successfully");
		    return reKey;
	}
	
	/*	
	(reKey        TEXT PRIMARY KEY   NOT NULL," +
			"appKey        TEXT NOT NULL,"+
			"reviewerEmail TEXT," + 
			"decision      TEXT," + 
			"comments 
	*/

	public static Review getReviewByKey(String reKey) throws CustomException, ClassNotFoundException, SQLException {
		Connection conn = DataSource.getConnection();
		Review rev = new Review();
		System.out.println("Creating statement select RIVIEWS");
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM REVIEWS where REVIEWS.reKey = "+"'"+reKey+"'";
		System.out.println(sql);
		try{
			ResultSet rs = stmt.executeQuery(sql);
			String key = rs.getString("reKey");
			rev.setReKey(key);
			rev.setAppKey(rs.getString("appKey"));
			rev.setReviewerEmail(rs.getString("reviewerEmail"));
			rev.setDecision(rs.getString("decision"));
			rev.setComments(rs.getString("comments"));
			List <Link> links = new ArrayList<Link>();
			Link k = new Link("/reviews/"+key,"self");
			links.add(k);
			rev.setLink(links);
			rs.close();
		}
		catch (SQLException e){
			throw new CustomException(Status.NOT_FOUND,"review not exists");
		}finally{
			stmt.close();
			//conn.close();
			DataSource.returnConnection(conn);
		}
		return rev;		
	}
	
	private static boolean isReviewd(String appKey, String reviewerEmail) throws CustomException, ClassNotFoundException, SQLException {
		Connection conn = DataSource.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT reKey FROM REVIEWS where REVIEWS.reviewerEmail = "+"'"+reviewerEmail+"' and REVIEWS.appKey="+"'"+appKey+"';";
		System.out.println(sql);
	
		try{
			ResultSet rs = stmt.executeQuery(sql);
			rs.getString("reKey");
			rs.close();
		}
		catch (SQLException e){
			return false;
		}finally{
			stmt.close();
			//conn.close();
			DataSource.returnConnection(conn);
		}
		return true;		
	}



	public static ReviewList searchReviews(String email, String appKey) throws SQLException, ClassNotFoundException, CustomException {
		Connection conn = DataSource.getConnection();
		ReviewList revs = new ReviewList();
		Review rev = new Review();
		
		System.out.println("Creating statement search REVIEWS");
		Statement stmt = null;
		String sql = "SELECT * FROM REVIEWS where";
		int i = 0;
		if(email != null && !email.equals("")){
			sql+= " reviewerEmail="+"'"+email+"'";
			i++;
		}
		if(appKey != null && !appKey.equals("")){
			if(i!=0) sql+=" and";
			sql+= " appKey ="+"'"+appKey +"'";
			i++;
		}
		sql +=";";
		if(i==0) throw new CustomException(Status.BAD_REQUEST,"at least one parameter for search");
		System.out.println(sql);
		try{
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			rev.setAppKey(appKey);
			rev.setComments(rs.getString("comments"));
			rev.setDecision(rs.getString("decision"));
			rev.setReviewerEmail(rs.getString("reviewerEmail"));
			String key = rs.getString("reKey");
			rev.setReKey(key);
			List <Link> links = new ArrayList<Link>();
			Link k = new Link("/reviews/"+key,"details");
			links.add(k);
			rev.setLink(links);
			revs.getRev().add(rev);
		}
		rs.close();
		return revs;
		}catch (SQLException e){	
			System.out.println("____________________");
			System.out.println("____________________");
			System.out.println("____________________");
			System.out.println(sql);
			System.out.println(e.getMessage());
			throw new CustomException(Status.BAD_REQUEST,"Review not exists");
		}finally{
			stmt.close();
			//conn.close();
			DataSource.returnConnection(conn);
		}
	}
 
}