package api;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import dao.PollsDao;
import model.Poll;
public class PollAndVoteApi {
	
	static final String REST_URI = "http://localhost:8080/PollAVoteServer";
	
	public static PollsDao getPollById(String pid){
		WebClient jobClient = WebClient.create(REST_URI);
		String s = "";
		jobClient.path("/polls/"+ "'" + pid + "'").accept(MediaType.APPLICATION_JSON);
		s = jobClient.get(String.class);
		System.out.println("Get all polls.");
       // System.out.println(s);
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(s).getAsJsonObject().getAsJsonArray("polls");
        Type listType = new TypeToken<ArrayList<Poll>>(){}.getType();
        List<Poll> yourClassList = new Gson().fromJson(array, listType);
		return new PollsDao(yourClassList);
	}
	
	public static void createPoll( Poll poll){
		WebClient pollClient = WebClient.create(REST_URI);
		Form form = new Form();
		form.param("email", poll.getEmail());
		form.param("pollTitle", poll.getTitle());
		form.param("description", String.valueOf(poll.getDescription()));
		form.param("pollOptionType", poll.getPollOptionType());
		form.param("options", poll.getOptions());
		form.param("comments", poll.getComments());
		form.param("finalChoice", poll.getFinalChoice());
		pollClient.path("/polls").type(MediaType.APPLICATION_FORM_URLENCODED);
		pollClient.post(form);
		System.out.println("POST new Poll now --");
	}
	
	public static void deletePoll(String pid){
		WebClient jobClient = WebClient.create(REST_URI);
		jobClient.path("/polls/"+pid).delete();
		System.out.println("DELETed "+pid +" now --");	
	}
}
