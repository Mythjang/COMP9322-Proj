package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DB.DBOperation;
import api.JobManagerApi;
import dao.AppList;
import dao.JobsDao;
import model.Application;
import model.Job;
import model.Review;


@Controller
public class HireTeamController {
	
	
	@RequestMapping("/waittingApp")
	public ModelAndView jobList(HttpSession httpSession) throws IOException, ClassNotFoundException, SQLException{
		String email = (String) httpSession.getAttribute("userEmail");
		String mEmail = DBOperation.getWorkFor(email);
		System.out.println("managerEmail= "+mEmail);
		JobsDao jobs = JobManagerApi.searchJob(mEmail, "inReview");
		System.out.println("ahhhaahhaa ");
		System.out.println(jobs.toString());
		AppList apps = new AppList();
		for (Job job :jobs.getJobs()){
			apps.getApps().addAll(JobManagerApi.searchApps(null, job.getKey(), "app-reviewer").getApps());
			
		}
		System.out.println(apps.getApps().toString());
		return new ModelAndView("hireTeam/reApplicationPage","apps",apps);

	}
	
	
	@RequestMapping("/hireAppDetail")
	public ModelAndView manAppDetail(HttpSession httpSession, @RequestParam(value="appKey") String appKey) throws IOException{
		String email = (String) httpSession.getAttribute("userEmail");
		Application app = JobManagerApi.getApplicationByKey(appKey);
		List<Review> rev = JobManagerApi.searchReview(email,appKey);
		return new ModelAndView("hireTeam/reAppDetailPage","app",app);
	}
	
	@RequestMapping("/confirmApp")
	public String createReview(HttpSession httpSession, @RequestParam(value="appKey") String appKey,@RequestParam(value="comment") String comment,@RequestParam(value="decision") String decision ) throws IOException{
		String email = (String) httpSession.getAttribute("userEmail");
		JobManagerApi.createReview(email,appKey,comment,decision);
		return "redirect:/waittingApp";
	}
}
