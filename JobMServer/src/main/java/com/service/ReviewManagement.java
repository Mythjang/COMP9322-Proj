package com.service;

import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.dao.ReviewList;
import com.db.DBOperationReview;
import com.model.Review;




@Path("/reviews")
public class ReviewManagement {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;



	@RolesAllowed({Roles.CANDIDATE,Roles.MANAGER,Roles.REVIEWER})
	@GET
	@Path("/{reKey}")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Review> getReviewByKey(@PathParam("reKey") String reKey) throws ClassNotFoundException, SQLException, CustomException {
		Review re =  DBOperationReview.getReviewByKey(reKey);
		return new ResponseEntity<Review>(re,HttpStatus.OK);
	}



	@RolesAllowed({Roles.CANDIDATE,Roles.MANAGER,Roles.REVIEWER})
	@GET
	@Path("/search")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<ReviewList> searchReview(@QueryParam ("email") String email,@QueryParam ("appKey") String appKey) throws Exception {
		System.out.println("email "+email);
		System.out.println("appKey "+appKey);
		ReviewList rev = DBOperationReview.searchReviews(email,appKey);
		return new ResponseEntity<ReviewList>(rev,HttpStatus.OK);	
	}
	

	@RolesAllowed(Roles.REVIEWER)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<String> newReview(
			@FormParam ("appKey") String appKey,
			@FormParam("email") String reviewerEmail,
			@FormParam("decision") String decision,
			@FormParam("comments") String comments) throws ClassNotFoundException, SQLException, CustomException 
	{
		String id = DBOperationReview.createNewReview(appKey, reviewerEmail, decision, comments);
		return new ResponseEntity<String>(id, HttpStatus.CREATED);

	}
	



}			
	

