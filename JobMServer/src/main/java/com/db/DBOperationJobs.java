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
		 * 
		 * @param id
		 * @return
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 * @throws CustomException
		 */
		public static Job getJobById(String id) throws ClassNotFoundException, SQLException, CustomException {
			Connection conn = DataSource.getConnection();
			Job job = new Job();
			System.out.println("Creating statement select job");
			Statement stmt =null;
			String sql = "SELECT * FROM JOBS where JOBS.jobId = "+"'"+id+"'";
			System.out.println(sql);
			try{
				stmt = conn.createStatement();
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
			//	conn.close();
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
		try{
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
		}catch(SQLException e){
			
		}finally{
			
		stmt.close();
	//	conn.close();
		DataSource.returnConnection(conn);
		}
		return new JobsList(jobs);
	}

	
	

	public static String addJob(String email, String companyName, double salaryRate, String positionType, String location,
		String detail, String status) throws ClassNotFoundException, SQLException, CustomException {
		Connection conn = DataSource.getConnection();
		String id = UUID.randomUUID().toString();
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO JOBS (jobId,email,companyName,salaryRate,positionType,location,detail,status) " +
		"VALUES ('"+id+"','"+email+"','"+companyName+"',"+salaryRate+",'"+positionType+"','"+location+"','"+detail+"','"+status+ "');"; 
		stmt.executeUpdate(sql);
		stmt.close();
	//	conn.close();
		DataSource.returnConnection(conn);
	    System.out.println("Records created successfully");
	    return String.valueOf(id);
	}

	
	public static void deleteJobById(String id) throws ClassNotFoundException, SQLException, CustomException {
		Job job = DBOperationJobs.getJobById(id);
		if(!job.getStatus().equals("open") && !job.getStatus().equals("close"))
			throw new CustomException(Status.BAD_REQUEST,"Status of job is "+job.getStatus()+", which not satisfy condition of delete"); 
		Connection conn = DataSource.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "delete from JOBS where jobId="+"'"+id+"'" ;
		stmt.executeUpdate(sql);
		stmt.close();
	//	conn.close();
		System.out.println("Opened database successfully");
		DataSource.returnConnection(conn);
	}
	
	



	public static JobsList searchJob(String email, String companyName, double salaryRate, String positionType, String location,
			String status) throws ClassNotFoundException, SQLException, CustomException {
		Connection conn = DataSource.getConnection();
		List<Job> jobs = new ArrayList<Job>();
		System.out.println("Creating statement search jobs");
		Statement stmt = null;
		
		String sql = "SELECT * FROM JOBS where";
		int i = 0;
		if(email != null && !email.equals("")){
			sql+= " email="+"'"+email+"'";
			i++;
		}
		if(companyName != null && !companyName.equals("")) {
			if(i!=0) sql+=" and";
			sql+= " companyName like "+"'%"+companyName+"%'";
			i++;
		}
		if(salaryRate != -1){
			if(i!=0) sql+=" and";
			sql+= " salaryRate >= "+salaryRate;
			i++;
		}
		if(positionType != null && !positionType.equals("")){
			if(i!=0) sql+=" and";
			sql+= " positionType like "+"'%"+positionType+"%'";
			i++;
		}
		if(location != null && !location.equals("")){
			if(i!=0) sql+=" and";
			sql+= " location like "+"'%"+location +"%'";
			i++;
		}
		if(status != null && !status.equals("")){
			if(i!=0) sql+=" and";
			sql+= " status ="+"'"+status +"'";
			i++;
		}
		System.out.println(sql);System.out.println(sql);System.out.println(sql);
		if(i==0) throw new CustomException(Status.BAD_REQUEST,"at least one parameter for search");
		sql +=";";
		System.out.println(sql);
		
		try{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Job job;
	
			while(rs.next()){
				job = new Job();
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
		}catch(SQLException e){
			throw new CustomException(Status.BAD_REQUEST,"jobs not exists");
		}finally{
			stmt.close();
		//	conn.close();
			DataSource.returnConnection(conn);
		}
		return new JobsList(jobs);
	}


	public static Job updateJobById(String id,String companyName, double salaryRate, String positionType,
			String location, String detail, String status) throws ClassNotFoundException, SQLException, CustomException {
		
		Job job = DBOperationJobs.getJobById(id);
		int preStatus = StatusDao.instance.getStatus().get(job.getStatus());
		int currStatus = StatusDao.instance.getStatus().get(status);
		
		if(preStatus>currStatus) throw new CustomException(Status.BAD_REQUEST,"can not roll status back"); 
	
		Connection conn =  DataSource.getConnection();

	    Statement stmt = conn.createStatement();
	    //try{
		    String sql = "UPDATE JOBS" +
		              " set companyName="+"'"+companyName+"'"+", salaryRate="+salaryRate+", positionType="+"'"+positionType+"'"+
		              ", location="+"'"+location+"'"+", detail="+"'"+detail+"'"+", status="+"'"+status+"'"+
		              " where jobId="+"'"+id+"'";
		    System.out.println(sql);
		    stmt.executeUpdate(sql);
		    job = DBOperationJobs.getJobById(id);
		    System.out.println("job update Successful");
		//}catch(SQLException e){
		//	throw  new CustomException(Status.BAD_REQUEST,"Salary rate must be real number"); 
		//}finally{
		    stmt.close();
		 //   conn.close();
		    DataSource.returnConnection(conn);
	//	}
		return job;
	}
	
	
	
	
	
	
	
	public static Job updateJobStatusById(String id) throws ClassNotFoundException, SQLException, CustomException {
		
		Job job = DBOperationJobs.getJobById(id);
		
		Connection conn =  DataSource.getConnection();

	    Statement stmt = conn.createStatement();
	    //try{
		    String sql = "UPDATE JOBS" +
		              " set status= 'appReceived'"+
		              " where jobId="+"'"+id+"'";
		    System.out.println(sql);
		    stmt.executeUpdate(sql);
		    job = DBOperationJobs.getJobById(id);
		    System.out.println("job status update Successful");
		//}catch(SQLException e){
		//	throw  new CustomException(Status.BAD_REQUEST,"Salary rate must be real number"); 
		//}finally{
		    stmt.close();
		  //  conn.close();
		    DataSource.returnConnection(conn);
	//	}
		return job;
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
		//conn.close();
		DataSource.returnConnection(conn);
	    System.out.println("jobTable created successfully");
	}


}