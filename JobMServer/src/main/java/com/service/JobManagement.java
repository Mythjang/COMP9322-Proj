package com.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.customException.CustomException;
import com.dao.JobsList;
import com.db.DBOperationJobs;
import com.model.Job;


@Path("/jobs")
public class JobManagement {
	// Allows to insert contextual objects into the class, 
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	/**
	 *  Return the list of jobs
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@PermitAll
	@GET
	@Produces("application/json")
	public Response getJobs() throws ClassNotFoundException, SQLException {
		// DBOperationJobs.createJobDB();
		 //DBOperation.addJob(System.currentTimeMillis(), "12@gmail.com","bb", 3, "c1r", "aa", "v", "n/a");
		Response.ResponseBuilder rb = Response.ok(DBOperationJobs.getAllJobs());
		return rb.build();

	}


	
	/**
	 *   @param jobId
	 * 	 @return a job by id
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws CustomException 
	 * @throws UserWebServiceException 
	 */
	@RolesAllowed({Roles.CANDIDATE,Roles.MANAGER,Roles.REVIEWER})
	@GET
	@Path("/{job}")
	@Produces({MediaType.APPLICATION_JSON})
	public Job getJobsById(@PathParam("job") String jobId) throws ClassNotFoundException, SQLException, CustomException {
		Job b =new Job();
		b =  DBOperationJobs.getJobById(jobId);
		return b;
	}


	// search jobs
	@RolesAllowed({Roles.CANDIDATE,Roles.MANAGER,Roles.REVIEWER})
	@GET
	@Path("/search")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<JobsList> searchJob(
		@QueryParam ("email") String email,
		@QueryParam ("companyName") String companyName,
		@DefaultValue("-1") @QueryParam ("salaryRate") double salaryRate,
		@QueryParam ("positionType") String positionType,
		@QueryParam ("location") String location,
		@QueryParam ("status") String status
	) throws Exception {
		JobsList jobs = DBOperationJobs.searchJob(email, companyName, salaryRate, positionType, location,status);
		if (jobs == null) return new ResponseEntity<JobsList>(HttpStatus.NO_CONTENT);
			//res = Response.status(Response.Status.NO_CONTENT).build();
		else return new ResponseEntity<JobsList>(jobs,HttpStatus.OK);
		
	}

	

	/**
	 * get all jobs of the user
	 * @param user
	 * @return all jobs of the user
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */



	
	/**
	 * create a new job
	 * @param jobId
	 * @param companyName
	 * @param salaryRate
	 * @param positionType
	 * @param location
	 * @param detail
	 * @param status
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException
	 */
	@RolesAllowed(Roles.MANAGER)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ResponseEntity<String> newJob(
			@FormParam ("email") String email,
			@FormParam("companyName") String companyName,
			@FormParam("salaryRate") double salaryRate,
			@FormParam("positionType") String positionType,
			@FormParam("location") String location,
			@FormParam("detail") String detail,
			@FormParam("status") String status) throws ClassNotFoundException, SQLException 
	{
		String id = DBOperationJobs.addJob(email,companyName,salaryRate,positionType,location,detail,status);
		return new ResponseEntity<String>(id, HttpStatus.CREATED);

	}
	@PUT
	@Path("{job}")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Job> putJob(
			@PathParam("job") String id,
			@FormParam("companyName") String companyName,
			@FormParam("salaryRate") double salaryRate,
			@FormParam("positionType") String positionType,
			@FormParam("location") String location,
			@FormParam("detail") String detail,
			@FormParam("status") String status) throws ClassNotFoundException, SQLException, CustomException {
		Job updatedjob = DBOperationJobs.updateJobById(id,companyName,salaryRate,positionType,location,detail,status);
		return new ResponseEntity<Job>(updatedjob, HttpStatus.CREATED);
	}
	/**
	 * Delete job by id 
	 * @param id
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@RolesAllowed(Roles.MANAGER)
	@DELETE
	@Path("{job}")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<String> deleteJob(@PathParam("job") String id) throws ClassNotFoundException, SQLException {
		DBOperationJobs.deleteJobById(id);
		
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}
	



}			
	

