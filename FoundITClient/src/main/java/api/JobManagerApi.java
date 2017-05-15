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

import dao.JobsDao;
import model.Job;

public class JobManagerApi {
static final String REST_URI = "http://localhost:8080/JobManageServer";
	
	public static JobsDao getJobByUser(String user){
		WebClient jobClient = WebClient.create(REST_URI);
		String s = "";
		jobClient.path("/jobs/user/"+user).accept(MediaType.APPLICATION_JSON);
		s = jobClient.get(String.class);
		System.out.println("Get all jobs --");
       // System.out.println(s);
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(s).getAsJsonObject().getAsJsonArray("jobs");
        Type listType = new TypeToken<ArrayList<Job>>(){}.getType();
        List<Job> yourClassList = new Gson().fromJson(array, listType);
		return new JobsDao(yourClassList);
	}
	
	
	
	public static void createJob(String email, Job job){
		WebClient jobClient = WebClient.create(REST_URI);
		Form form = new Form();
		form.param("email", email);
		form.param("companyName", job.getCompanyName());
		form.param("salaryRate", String.valueOf(job.getSalaryRate()));
		form.param("positionType", job.getPositionType());
		form.param("location", job.getLocation());
		form.param("detail", job.getDetail());
		form.param("status", "open");
		jobClient.path("/jobs").type(MediaType.APPLICATION_FORM_URLENCODED);
		jobClient.post(form);
		System.out.println("POSTed new Job now --");
	}
	 
	public static void deleteJob(String jobId){
		WebClient jobClient = WebClient.create(REST_URI);
		jobClient.path("/jobs/"+jobId).delete();
		System.out.println("DELETed "+jobId +" now --");	
	}
	
	
}
