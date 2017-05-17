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
import com.dao.AppList;
import com.model.Application;
import com.model.Job;
import com.model.Link;

public class DBOperationApplications {

	
	public static void createApplicationDB() throws ClassNotFoundException, SQLException{
		
		Connection conn = DataSource.getConnection();
		System.out.println("Opened database successfully");
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE APPS" +
				"(appKey TEXT PRIMARY KEY   NOT NULL," +
				" jobId		TEXT NOT NULL,"+
				" email      TEXT," + 
				" dob       TEXT," + 
				" name     TEXT," + 
				" title    	   TEXT," + 
				" currentPosition            TEXT," + 
				" education       TEXT," + 
				" pastExperience     TEXT," + 
				" professionalSkills    	   TEXT," + 
				" coverLetter            TEXT," +
				"FOREIGN KEY(jobId)   REFERENCES JOBS(jobId)"+ ")"; 
		stmt.executeUpdate(sql);
		stmt.close();
	//	conn.close();
		DataSource.returnConnection(conn);
	    System.out.println("AppTable created successfully");
	}
	
	
/*	 appKey TEXT PRIMARY KEY NOT NULL,
	    jobId TEXT,
	    email TEXT,
	    dob TEXT,
	    name TEXT,
	    title TEXT,
	    currentPosition TEXT,
	    education TEXT,
	    pastExperience TEXT,
	    professionalSkills TEXT,
	    coverLetter TEXT,*/
	public static Application getAppByAppKey(String appKey) throws ClassNotFoundException, SQLException, CustomException {
		Connection conn = DataSource.getConnection();
		Application app = new Application();
		System.out.println("Creating statement select app");
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM APPS where APPS.appKey = "+"'"+appKey+"'";
		System.out.println(sql);
		try{
			ResultSet rs = stmt.executeQuery(sql);
			String key = rs.getString("appKey");
			app.setAppKey(key);
			app.setJobId(rs.getString("jobId"));
			app.setEmail(rs.getString("email"));
			app.setDob(rs.getString("dob"));
			app.setName(rs.getString("name"));
			app.setTitle(rs.getString("title"));
			app.setCurrentPosition(rs.getString("currentPosition"));
			app.setEducation(rs.getString("education"));
			app.setPastExperience(rs.getString("pastExperience"));
			app.setProfessionalSkills(rs.getString("professionalSkills"));
			app.setCoverLetter(rs.getString("coverLetter"));
			List <Link> links = new ArrayList<Link>();
			Link k = new Link("/apps/"+key,"self");
			Link k2 = new Link("/reviews/search?appKey="+key,"reviews");
			links.add(k);
			links.add(k2);
			app.setLink(links);
			rs.close();
		}
		catch (SQLException e){
			throw new CustomException(Status.NOT_FOUND,"Application not exists");
		}finally{
			stmt.close();
		//	conn.close();
			DataSource.returnConnection(conn);
		}
		return app;
	}
 
	
	
	public static AppList searchApps(String email, String jobId) throws ClassNotFoundException, SQLException, CustomException {
		Connection conn = DataSource.getConnection();
		List<Application> apps = new ArrayList<Application>();
		Application app = new Application();
		System.out.println("Creating statement search APPS");
		Statement stmt = null;
		String sql = "SELECT * FROM APPS where";
		int i = 0;
		if(email != null && !email.equals("")){
			sql+= " email="+"'"+email+"'";
			i++;
		}
		if(jobId != null&& !jobId.equals("")){
			if(i!=0) sql+=" and";
			sql+= " jobId ="+"'"+jobId +"'";
			i++;
		}
		
		sql +=";";
		if (i==0) throw new CustomException(Status.BAD_REQUEST,"at least one parameter for search");
	
		System.out.println(sql);
		try{
			
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			app = new Application();
			app.setAppKey(rs.getString("appKey"));
			app.setJobId(rs.getString("jobId"));
			app.setEmail(rs.getString("email"));
			app.setDob(rs.getString("dob"));
			app.setName(rs.getString("name"));
			app.setTitle(rs.getString("title"));
			app.setCurrentPosition(rs.getString("currentPosition"));
			app.setEducation(rs.getString("education"));
			app.setPastExperience(rs.getString("pastExperience"));
			app.setProfessionalSkills(rs.getString("professionalSkills"));
			app.setCoverLetter(rs.getString("coverLetter"));
			List <Link> links = new ArrayList<Link>();
			Link k = new Link("/apps/"+app.getAppKey(),"details");
			links.add(k);
			app.setLink(links);
			apps.add(app);
		}
		rs.close();
		}catch(SQLException e){
			System.out.println("____________________");
			System.out.println("____________________");
			System.out.println("____________________");
			System.out.println(sql);
			System.out.println(e.getMessage());
			throw new CustomException(Status.BAD_REQUEST,"app not exists");
		}finally{
			stmt.close();
			//conn.close();

			DataSource.returnConnection(conn);
		}
		return new AppList(apps);
	}
	
	
	public static void deleteAppByKey(String key) throws ClassNotFoundException, SQLException, CustomException {
		Application app = DBOperationApplications.getAppByAppKey(key);
		String status = DBOperationJobs.getJobById(app.getJobId()).getStatus();
		if(!status.equals("open") && !status.equals("appReceived")&& !status.equals("close")) throw new CustomException(Status.BAD_REQUEST,"your application can not be deleted at current job status= "+status);
		Connection conn = DataSource.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "delete from APPS where APPS.appKey="+"'"+key+"'" ;
		stmt.executeUpdate(sql);
		stmt.close();
		//conn.close();
		System.out.println("detete success");
		DataSource.returnConnection(conn);
	}


	public static String addNewApplication(String jobId, String email, String dob, String name, String title,
			String currentPosition, String education, String pastExperience, String professionalSkills,
			String coverLetter) throws ClassNotFoundException, SQLException, CustomException {
			Job job = DBOperationJobs.getJobById(jobId);
			
			if (DBOperationApplications.searchApps(email, jobId).getApps().size()>0) {
				throw new CustomException(Status.BAD_REQUEST,"You have been applyed this job before"); 
			
			}
			System.out.println("status= "+job.getStatus());
			if(!job.getStatus().equals("open") && !job.getStatus().equals("appReceived")) throw new CustomException(Status.BAD_REQUEST,"job status is not open or appReceived");
			Connection conn = DataSource.getConnection();
			String appKey = UUID.randomUUID().toString();
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO APPS (appKey,jobId,email,dob,name,title,currentPosition,education,pastExperience,professionalSkills,coverLetter) " +
			"VALUES ('"+appKey+"','"+jobId+"','"+email+"',"+dob+",'"+name+"','"+title+"','"+currentPosition+"','"+education+"','"+pastExperience+"','"+professionalSkills+"','"+coverLetter+ "');"; 
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			//conn.close();
			DataSource.returnConnection(conn);
			DBOperationJobs.updateJobStatusById(jobId);
			System.out.println("Records created successfully");
		    return String.valueOf(appKey);
	}


	public static Application updateAppByKey(String appKey, String coverLetter) throws ClassNotFoundException, SQLException, CustomException {
		Application app = DBOperationApplications.getAppByAppKey(appKey);
		String status = DBOperationJobs.getJobById(app.getJobId()).getStatus();
		if(!status.equals("open") && !status.equals("appReceived")) throw new CustomException(Status.BAD_REQUEST,"current job status is "+status+ ", it is too late to update");
		app = new Application();
		Connection conn =  DataSource.getConnection();
		System.out.println("start");
	    Statement stmt = conn.createStatement();
	    try{
		    String sql = "UPDATE APPS" +
		              " set coverLetter="+"'"+coverLetter+"'"+
		              " where appKey="+"'"+appKey+"'";
		    System.out.println(sql);
		    stmt.executeUpdate(sql);
		    app = DBOperationApplications.getAppByAppKey(appKey);
		    System.out.println("app update Successful");
		}catch(SQLException e){
			throw new CustomException(Status.BAD_REQUEST,"Application "+appKey+" not exist"); 
		}finally{
		    stmt.close();
		 //   conn.close();
		    DataSource.returnConnection(conn);
		}
		return app;
	}


//	public static AppList getSuccessApp(String jobId) throws ClassNotFoundException, SQLException {
//		AppList apps = DBOperationApplications.searchApps(null, jobId);
//		AppList success = new AppList();
//		for(Application app : apps.getApps()){
//			if(DBOperationReview.getRecByTwice(app.getAppKey()))
//				success.getApps().add(app);
//			
//		}
//		return success;
//	}



	
	

	

}
