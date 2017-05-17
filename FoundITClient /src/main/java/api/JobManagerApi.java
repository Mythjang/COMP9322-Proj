package api;



import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import dao.AppList;
import dao.JobsDao;
import dao.ReviewList;
import model.Application;
import model.Job;
import model.Review;

public class JobManagerApi {
static final String REST_URI = "http://localhost:8085/JobMServer";
	
	public static JobsDao searchJobByUserOrStatus(String user,String status){
		WebClient jobClient = WebClient.create(REST_URI);
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey","app-candidate");
		String s = "";
		jobClient = jobClient.path("/jobs/search").accept(MediaType.APPLICATION_JSON);
		if (user!=null) jobClient = jobClient.query("email", user);
		if (status!=null) jobClient = jobClient.query("status", status);
		s = jobClient.get(String.class);
		System.out.println("Get all jobs of "+user);
        System.out.println(s);
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(s).getAsJsonObject().getAsJsonObject("body").getAsJsonArray("jobs");
        Type listType = new TypeToken<ArrayList<Job>>(){}.getType();
        JobsDao jobs = new JobsDao(new Gson().fromJson(array, listType));
		return jobs;
	}
	
	


	
	public static void updateJob(Job job){
		WebClient jobClient = WebClient.create(REST_URI);
		Form form = new Form();
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey","app-manager");
		form.param("companyName", job.getCompanyName());
		form.param("salaryRate", String.valueOf(job.getSalaryRate()));
		form.param("positionType", job.getPositionType());
		form.param("location", job.getLocation());
		form.param("detail", job.getDetail());
		form.param("status", job.getStatus());
		jobClient.path("/jobs/"+job.getKey()).type(MediaType.APPLICATION_FORM_URLENCODED);
		jobClient.put(form);
		System.out.println("update Job now --");
	}
	
	
	public static void createJob(String email, Job job){
		WebClient jobClient = WebClient.create(REST_URI);
		Form form = new Form();
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey","app-manager");
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
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey","app-manager");
		jobClient.path("/jobs/"+jobId).delete();
		System.out.println("DELETed "+jobId +" now --");	
	}



	public static JobsDao getOpenJob() {
		WebClient jobClient = WebClient.create(REST_URI);
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey","app-candidate");
		String s = "";
		jobClient.path("/jobs").accept(MediaType.APPLICATION_JSON);
		s = jobClient.get(String.class);
		System.out.println("Get all avaiable jobs --");
        System.out.println(s);
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(s).getAsJsonObject().getAsJsonObject("body").getAsJsonArray("jobs");
        Type listType = new TypeToken<ArrayList<Job>>(){}.getType();
        JobsDao jobs = new JobsDao(new Gson().fromJson(array, listType));
		return jobs;
	}


	public static Job getJobById(String id) {
		WebClient jobClient = WebClient.create(REST_URI);
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey","app-manager");
		String s = "";
		jobClient.path("/jobs/"+id).accept(MediaType.APPLICATION_JSON);
		s = jobClient.get(String.class);
		System.out.println("Get  job --"+id);
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(s).getAsJsonObject();
        Job job = new Gson().fromJson(obj,Job.class);
		return job;
	
	}



	public static void createApplication(Application app) {
		WebClient jobClient = WebClient.create(REST_URI);
		Form form = new Form();
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey","app-candidate");
		form.param("jobId",app.getJobId() );
		form.param("dob", app.getDob());
		form.param("email", app.getEmail());
		form.param("name", app.getName());
		form.param("title", app.getTitle());
		form.param("currentPosition", app.getCurrentPosition());
		form.param("education", app.getEducation());
		form.param("pastExperience", app.getPastExperience());
		form.param("professionalSkills", app.getProfessionalSkills());
		form.param("coverLetter", app.getCoverLetter());
		jobClient.path("/apps").type(MediaType.APPLICATION_FORM_URLENCODED);
		jobClient.post(form);
		System.out.println("POSTed new Job now --");
	}





	public static AppList searchApps(String email, String jobId, String role) {
		WebClient jobClient = WebClient.create(REST_URI);
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey",role);
		String s = "";
		jobClient = jobClient.path("/apps/search").accept(MediaType.APPLICATION_JSON);
		if (email!=null) jobClient = jobClient.query("email", email);
		if (jobId!=null) jobClient = jobClient.query("jobId", jobId);
		s = jobClient.get(String.class);
		//System.out.println("Get all applications of "+email);
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(s).getAsJsonObject().getAsJsonObject("body").getAsJsonArray("apps");
        Type listType = new TypeToken<ArrayList<Application>>(){}.getType();
        AppList appList = new AppList(new Gson().fromJson(array, listType));

		return appList;
	}





	public static Application getApplicationByKey(String appKey) {
		WebClient jobClient = WebClient.create(REST_URI);
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey","app-candidate");
		String s = "";
		jobClient.path("/apps/"+appKey).accept(MediaType.APPLICATION_JSON);
		s = jobClient.get(String.class);
		System.out.println("Get  app --"+appKey);
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(s).getAsJsonObject().getAsJsonObject("body");
        Application app = new Gson().fromJson(obj,Application.class);
		return app;
	}





	public static void updateApp(String coverLetter, String key) {
		WebClient jobClient = WebClient.create(REST_URI);
		Form form = new Form();
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey","app-candidate");
		form.param("coverLetter", coverLetter);
		jobClient.path("/apps/"+key).type(MediaType.APPLICATION_FORM_URLENCODED);
		jobClient.put(form);
		System.out.println("update app now --");	
	}





	public static void createReview(String email, String appKey, String comments, String decision) {
		WebClient jobClient = WebClient.create(REST_URI);
		Form form = new Form();
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey","app-reviewer");
		form.param("email", email);
		form.param("appKey", appKey);
		form.param("comments", comments);
		form.param("decision", decision);
		jobClient.path("/reviews").type(MediaType.APPLICATION_FORM_URLENCODED);
		jobClient.post(form);
	}





	public static AppList getPassedApplication(String jobId) {
		AppList apps = new AppList();
		for(Application app :JobManagerApi.searchApps(null, jobId, "app-manager").getApps()){
			ReviewList revs = JobManagerApi.searchReview(null, app.getAppKey(), "app-manager");
			int count = 0;
			if(revs != null) {
				for(Review rev: revs.getRev()){
					System.out.println("----------");
					System.out.println(rev.getDecision());
					if(rev.getDecision().equals("recommend")) count++;
				}
				if(count >= 2) apps.getApps().add(app);
			}	
		}
		return apps;
	}






	
	public static ReviewList searchReview(String email, String appKey, String role) {
		WebClient jobClient = WebClient.create(REST_URI);
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey",role);
		String s = "";
		jobClient = jobClient.path("/reviews/search").accept(MediaType.APPLICATION_JSON);
		if (email!=null && !email.equals("")) jobClient = jobClient.query("email", email);
		if (appKey!=null && !appKey.equals("")) jobClient = jobClient.query("appKey", appKey);
		s = jobClient.get(String.class);
        System.out.println(s);
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(s).getAsJsonObject().getAsJsonObject("body").getAsJsonArray("rev");
        if (array.size() ==0) return null;
        Type listType = new TypeToken<ArrayList<Review>>(){}.getType();
        ReviewList revs = new ReviewList();
        revs.setRev(new Gson().fromJson(array, listType));
		return revs;
	}


//	WebClient jobClient = WebClient.create(REST_URI);
//	jobClient.header("securityKey", "i-am-foundit");
//	jobClient.header("shortKey",role);
//	String s = "";
//	jobClient = jobClient.path("/apps/search").accept(MediaType.APPLICATION_JSON);
//	if (email!=null) jobClient = jobClient.query("email", email);
//	if (jobId!=null) jobClient = jobClient.query("jobId", jobId);
//	s = jobClient.get(String.class);
//	//System.out.println("Get all applications of "+email);
//    JsonParser parser = new JsonParser();
//    JsonArray array = parser.parse(s).getAsJsonObject().getAsJsonObject("body").getAsJsonArray("apps");
//    Type listType = new TypeToken<ArrayList<Application>>(){}.getType();
//    AppList appList = new AppList(new Gson().fromJson(array, listType));
	
	public static JobsDao AdvancedSearchJob(String companyName, String positionType, String salaryRate, String location,
			String status) {
		WebClient jobClient = WebClient.create(REST_URI);
		jobClient.header("securityKey", "i-am-foundit");
		jobClient.header("shortKey","app-candidate");
		String s = "";
		System.out.println("companyName================="+companyName);
		jobClient = jobClient.path("/jobs/search").accept(MediaType.APPLICATION_JSON);
		if (companyName!=null &&!companyName.equals("")) jobClient = jobClient.query("companyName", companyName);
		if (positionType!=null&&!positionType.equals("")) jobClient = jobClient.query("positionType", positionType);
		if (salaryRate!=null&&!salaryRate.equals("")) jobClient = jobClient.query("salaryRate", salaryRate);
		if (location!=null&&!location.equals("")) jobClient = jobClient.query("location", location);
		if (status!=null&&!status.equals("")) jobClient = jobClient.query("status", status);
		s = jobClient.get(String.class);
		System.out.println(s);
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(s).getAsJsonObject().getAsJsonObject("body").getAsJsonArray("jobs");
		Type listType = new TypeToken<ArrayList<Job>>(){}.getType();
		JobsDao jobs = new JobsDao(new Gson().fromJson(array, listType));
		return jobs;
	}
}
