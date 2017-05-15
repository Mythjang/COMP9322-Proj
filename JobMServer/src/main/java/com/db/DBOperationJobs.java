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
import com.dao.JobsList;
import com.dao.StatusDao;
import com.model.Job;
import com.model.Link;





public class DBOperationJobs
{
 
	
	 /**
	  * get job by job id
	  * @param id
	  * @return a single job
	  * @throws ClassNotFoundException
	  * @throws SQLException
	 * @throws CustomException 
	 * @throws UserWebServiceException 
	  */
		public static Job getJobById(String id) throws ClassNotFoundException, SQLException, CustomException {
			Connection conn = DataSource.getConnection();
			Job job = new Job();
			System.out.println("Creating statement select job");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM JOBS where JOBS.jobId = "+"'"+id+"'";
			System.out.println(sql);
			try{
				job = new Job();
				ResultSet rs = stmt.executeQuery(sql);
				String jobId = rs.getString("jobId");
				job.setKey(jobId);
				job.setCompanyName(rs.getString("companyName"));
				job.setSalaryRate(rs.getDouble("salaryRate"));
				job.setPositionType(rs.getString("positionType"));
				job.setLocation(rs.getString("location"));
				job.setDetail(rs.getString("detail"));
				job.setStatus(rs.getString("status"));
				List <Link> links = new ArrayList<Link>();
				Link k = new Link("/jobs/"+jobId,"self");
				Link k2 = new Link("/jobs","allJobs");
				links.add(k);
				links.add(k2);
				job.setLink(links);
				rs.close();
			}
			catch (SQLException e){
				throw new CustomException(Status.NOT_FOUND,"job not exists");
				
			}finally{
				stmt.close();
				conn.close();
				DataSource.returnConnection(conn);
			}
			return job;
		}
	 
		
	
	/**
	 * get all jobs
	 * @return list of job
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static JobsList getAllJobs() throws ClassNotFoundException, SQLException {
		Connection conn = DataSource.getConnection();
		Statement stmt = null;
		List<Job> jobs = new ArrayList<Job>();
		System.out.println("Creating statement select all jobs");
		stmt = conn.createStatement();
		String sql = "SELECT * FROM JOBS;";
		ResultSet rs = stmt.executeQuery(sql);
		Job job;
		while(rs.next()){
			job = new Job();
			String jobId = rs.getString("jobId");
			job.setKey(jobId);
			job.setCompanyName(rs.getString("companyName"));
			job.setSalaryRate(rs.getDouble("salaryRate"));
			job.setPositionType(rs.getString("positionType"));
			job.setLocation(rs.getString("location"));
			job.setDetail(rs.getString("detail"));
			job.setStatus(rs.getString("status"));
			List <Link> links = new ArrayList<Link>();
			Link k = new Link("/jobs/"+jobId,"details");
			links.add(k);
			job.setLink(links);
			jobs.add(job);
		}
		rs.close();
		stmt.close();
		conn.close();
		DataSource.returnConnection(conn);

		return new JobsList(jobs);
	}

	
	

	public static String addJob(String email, String companyName, double salaryRate, String positionType, String location,
		String detail, String status) throws ClassNotFoundException, SQLException {
		Connection conn = DataSource.getConnection();
		String id = UUID.randomUUID().toString();
		System.out.println("Opened database successfully");
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO JOBS (jobId,email,companyName,salaryRate,positionType,location,detail,status) " +
		"VALUES ('"+id+"','"+email+"','"+companyName+"',"+salaryRate+",'"+positionType+"','"+location+"','"+detail+"','"+status+ "');"; 
		System.out.println(sql);
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
		DataSource.returnConnection(conn);
	    System.out.println("Records created successfully");
	    return String.valueOf(id);
	}

	
	public static void deleteJobById(String id) throws ClassNotFoundException, SQLException {
		Connection conn = DataSource.getConnection();
	      System.out.println("Opened database successfully");
	      Statement stmt = conn.createStatement();
	      String sql = "delete from JOBS where jobId="+"'"+id+"'" ;
	      System.out.println(sql);
	      stmt.executeUpdate(sql);
	      stmt.close();
	      conn.close();
	      DataSource.returnConnection(conn);
	}
	
	


	
	public static void createJobDB() throws ClassNotFoundException, SQLException{
		Connection conn = DataSource.getConnection();
		System.out.println("Opened database successfully");
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE JOBS" +
				"(jobId TEXT PRIMARY KEY   NOT NULL," +
				" email			 TEXT,"+
				" companyName      TEXT," + 
				" salaryRate       REAL," + 
				" positionType     TEXT," + 
				" location    	   TEXT," + 
				" detail            TEXT," + 
				" status            TEXT)"; 
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
		DataSource.returnConnection(conn);
	    System.out.println("jobTable created successfully");
	}


	public static JobsList searchJob(String email, String companyName, double salaryRate, String positionType, String location,
			String status) throws ClassNotFoundException, SQLException {
		Connection conn = DataSource.getConnection();
		List<Job> jobs = new ArrayList<Job>();
		System.out.println("Creating statement search jobs");
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM JOBS where";
		int i = 0;
		if(email != null){
			sql+= " email="+"'"+email+"'";
			i++;
		}
		if(companyName != null) {
			if(i!=0) sql+=" and";
			sql+= " companyName="+"'"+companyName+"'";
			i++;
		}
		if(salaryRate != -1){
			if(i!=0) sql+=" and";
			sql+= " salaryRate="+salaryRate;
			i++;
		}
		if(positionType != null){
			if(i!=0) sql+=" and";
			sql+= " positionType="+"'"+positionType+"'";
			i++;
		}
		if(location != null){
			if(i!=0) sql+=" and";
			sql+= " location ="+"'"+location +"'";
			i++;
		}
		if(status != null){
			if(i!=0) sql+=" and";
			sql+= " status ="+"'"+status +"'";
		}
		sql +=";";
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		Job job;
			//STEP 5: Extract data from result set
		while(rs.next()){
			job = new Job();
			//Retrieve by column name
			String id = rs.getString("jobId");
			job.setKey(id);
			job.setCompanyName(rs.getString("companyName"));
			job.setSalaryRate(rs.getDouble("salaryRate"));
			job.setPositionType(rs.getString("positionType"));
			job.setLocation(rs.getString("location"));
			job.setDetail(rs.getString("detail"));
			job.setStatus(rs.getString("status"));
			List <Link> links = new ArrayList<Link>();
			Link k = new Link("/jobs/"+id,"details");
			links.add(k);
			job.setLink(links);
			jobs.add(job);
		}
		rs.close();
		stmt.close();
		conn.close();
		DataSource.returnConnection(conn);
		return new JobsList(jobs);
	}


	public static Job updateJobById(String id,String companyName, double salaryRate, String positionType,
			String location, String detail, String status) throws ClassNotFoundException, SQLException, CustomException {
		System.out.println(status);
		Job job = DBOperationJobs.getJobById(id);
	    System.out.println(job.toString());
		int preStatus = StatusDao.instance.getStatus().get(job.getStatus());
		int currStatus = StatusDao.instance.getStatus().get(status);
		System.out.println("status = "+preStatus+"afasf= "+ currStatus);
		if(preStatus>currStatus) throw new CustomException(Status.BAD_REQUEST,"can not roll status back"); 
		
		Connection conn =  DataSource.getConnection();
	    System.out.println("Opened database successfully");
	    Statement stmt = conn.createStatement();
	    String sql = "UPDATE JOBS" +
	              " set companyName="+"'"+companyName+"'"+", salaryRate="+salaryRate+", positionType="+"'"+positionType+"'"+
	              ", location="+"'"+location+"'"+", detail="+"'"+detail+"'"+", status="+"'"+status+"'"+
	              " where jobId="+"'"+id+"'";
	    System.out.println(sql);
	    stmt.executeUpdate(sql);
	    stmt.close();
	    conn.close();
	    DataSource.returnConnection(conn);
	    job = DBOperationJobs.getJobById(id);
		return job;
	}
	
	
//	public static void updateJobById(String id, String detail) throws SQLException, ClassNotFoundException {
//	 	Connection conn =  DataSource.getConnection();
//	    System.out.println("Opened database successfully");
//	    Statement stmt = conn.createStatement();
//	    String sql = "UPDATE JOBS" +
//	              " set user_name=?, sex=?, age=?, birthday=?, email=?, mobile=?,"+
//	              " update_user=?, update_date=CURRENT_DATE(), isdel=? "+
//	              " where jobId="+"'"+id+"'";
//	    System.out.println(sql);
//	    stmt.executeUpdate(sql);
//	    stmt.close();
//	    conn.close();
//	    DataSource.returnConnection(conn);
//	    System.out.println("Records delete successfully");
//
//	}

}