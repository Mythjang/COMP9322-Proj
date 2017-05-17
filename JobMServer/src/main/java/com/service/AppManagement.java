package com.service;

import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import javax.ws.rs.core.UriInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.customException.CustomException;
import com.dao.AppList;
import com.db.DBOperationApplications;
import com.model.Application;




@Path("/apps")
public class AppManagement {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;


	/**
	 * 
	 * @param appKey
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws CustomException
	 */
	@RolesAllowed({Roles.CANDIDATE,Roles.MANAGER,Roles.REVIEWER})
	@GET
	@Path("/{appKey}")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Application> getAppsByKey(@PathParam("appKey") String appKey) throws ClassNotFoundException, SQLException, CustomException {
		Application app =new Application();	
		app =  DBOperationApplications.getAppByAppKey(appKey);
		return new ResponseEntity<Application>(app,HttpStatus.OK);
	}


	/**
	 * 
	 * @param email
	 * @param jobId
	 * @return
	 * @throws Exception
	 */
	@RolesAllowed({Roles.CANDIDATE,Roles.MANAGER,Roles.REVIEWER})
	@GET
	@Path("/search")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<AppList> searchApp(@QueryParam ("email") String email,@QueryParam ("jobId") String jobId) throws Exception {
		System.out.println("email "+email);
		System.out.println("jobid "+jobId);
		AppList apps = DBOperationApplications.searchApps(email, jobId);
		return new ResponseEntity<AppList>(apps,HttpStatus.OK);	
	}
	
	/**
	 * 
	 * @param email
	 * @param companyName
	 * @param salaryRate
	 * @param positionType
	 * @param location
	 * @param detail
	 * @param status
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws CustomException
	 */
	

	@RolesAllowed(Roles.CANDIDATE)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<String> newApps(
			@FormParam ("jobId") String jobId,
			@FormParam("email") String email,
			@FormParam("dob") String dob,
			@FormParam("name") String name,
			@FormParam("title") String title,
			@FormParam ("currentPosition") String currentPosition,
			@FormParam("education") String education,
			@FormParam("pastExperience") String pastExperience,
			@FormParam("professionalSkills") String professionalSkills,
			@FormParam("coverLetter") String coverLetter) throws ClassNotFoundException, SQLException, CustomException 
	{
		String id = DBOperationApplications.addNewApplication(jobId,email,dob,name,title,currentPosition,education,pastExperience,professionalSkills,coverLetter);
		return new ResponseEntity<String>(id, HttpStatus.CREATED);

	}
	@RolesAllowed(Roles.CANDIDATE)
	@PUT
	@Path("{appKey}")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Application> putApp(
			@PathParam("appKey") String appKey,
			@FormParam("coverLetter") String coverLetter) throws ClassNotFoundException, SQLException, CustomException {
		Application app = DBOperationApplications.updateAppByKey(appKey, coverLetter);
		System.out.println("cacacascascs");
		return new ResponseEntity<Application>(app, HttpStatus.CREATED);
	}
	/**
	 * Delete job by id 
	 * @param id
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws CustomException 
	 */
	@RolesAllowed(Roles.CANDIDATE)
	@DELETE
	@Path("{appKey}")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<String> deleteApp(@PathParam("appKey") String appKey) throws ClassNotFoundException, SQLException, CustomException {
		DBOperationApplications.deleteAppByKey(appKey);;
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	


}			
	

