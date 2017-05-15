package au.edu.unsw.soacourse.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import au.edu.unsw.soacourse.dao.PollList;
import au.edu.unsw.soacourse.db.DBOperation;
import au.edu.unsw.soacourse.model.Poll;

@Path("/polls")
public class PollManagement {
	// Allows to insert contextual objects into the class, 
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	/**
	 *  Return the list of polls
	 * @throws SQLException 
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<PollList> getPolls() throws ClassNotFoundException, SQLException {
		//DBOperation.createPollsDB();
		//DBOperation.createVotesDB();
		PollList pl = DBOperation.getAllPolls();
		return new ResponseEntity<PollList>(pl, HttpStatus.OK);
	}
	
	
	/**
	 *   @param pid
	 * 	 @return a poll by id
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@GET
	@Path("/{Poll}")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Poll> getPollsById(@PathParam("Poll") String pid) throws Exception {
		System.out.println("add_pid: "+pid);
			Poll p =  DBOperation.getPollById(pid);
		if(p==null) return new ResponseEntity<Poll>(HttpStatus.NO_CONTENT);
		else return new ResponseEntity<Poll>(p, HttpStatus.OK);
	}
	
	/**
	 * create a new poll
	 * @param pid
	 * @param email
	 * @param pollTitle
	 * @param description
	 * @param pollOptionType
	 * @param options
	 * @param comments
	 * @param finalChoice
	 * @throws SQLException 
	 * @throws IOException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ResponseEntity<String> newPoll (
			@FormParam("email") String email,
			@FormParam("pollTitle") String pollTitle,
			@FormParam("description") String description,
			@FormParam("pollOptionType") String pollOptionType,
			@FormParam("options") String options,
			@FormParam("comments") String comments,
			@FormParam("finalChoice") String finalChoice) throws ClassNotFoundException, SQLException 
	{	
		System.out.println("haha:" + email);
		System.out.println(email);
		String id = DBOperation.addPoll(email,pollTitle,description,pollOptionType,options,comments,finalChoice);
		System.out.println("id:" + id);
		return new ResponseEntity<String>(id, HttpStatus.CREATED);

		//TODO: Fix here so that it returns the new Poll
	}
	
	/**
	 * Delete poll by id 
	 * @param id
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@RolesAllowed("ADMIN")
	@DELETE
	@Path("{poll}")
	public ResponseEntity<String> deletePoll(@PathParam("poll") String id) throws ClassNotFoundException, SQLException {
		DBOperation.deletePollById(id);
		
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}
	
}
