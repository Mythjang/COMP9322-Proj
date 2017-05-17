package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DB.DBOperation;
import api.JobManagerApi;
import dao.AppList;
import dao.JobsDao;
import dao.ReviewList;
import model.Application;
import model.Job;
import model.Review;


@Controller
public class HireTeamController {
	
	
	@RequestMapping("/processedApp")
	public ModelAndView jobList(HttpSession httpSession) throws IOException, ClassNotFoundException, SQLException{
		String email = (String) httpSession.getAttribute("userEmail");
		String mEmail = DBOperation.getWorkFor(email);
		System.out.println("managerEmail= "+mEmail);
		JobsDao jobs = JobManagerApi.searchJobByUserOrStatus(mEmail, "inReview");
		System.out.println("ahhhaahhaa ");
		System.out.println(jobs.toString());
		AppList apps = new AppList();
		for (Job job :jobs.getJobs()){
			System.out.println("key= "+job.getKey());
			for(Application app:JobManagerApi.searchApps(null, job.getKey(), "app-reviewer").getApps()){
				ReviewList revs = JobManagerApi.searchReview(email, app.getAppKey(), "app-reviewer");
				if (revs==null) continue;
				if(revs.getRev().size()>0);
				apps.getApps().add(app);
			}			
		}
		System.out.println(apps.getApps().toString());
		return new ModelAndView("hireTeam/reApplicationPage","apps",apps);

	}
	
	
	
	@RequestMapping("/pendingApp")
	public ModelAndView ajobList(HttpSession httpSession) throws IOException, ClassNotFoundException, SQLException{
		String email = (String) httpSession.getAttribute("userEmail");
		String mEmail = DBOperation.getWorkFor(email);
		System.out.println("managerEmail= "+mEmail);
		JobsDao jobs = JobManagerApi.searchJobByUserOrStatus(mEmail, "inReview");
		System.out.println("ahhhaahhaa ");
		System.out.println(jobs.toString());
		AppList apps = new AppList();
		for (Job job :jobs.getJobs()){
			System.out.println("key= "+job.getKey());
			for(Application app:JobManagerApi.searchApps(null, job.getKey(), "app-reviewer").getApps()){
				ReviewList revs = JobManagerApi.searchReview(null, app.getAppKey(), "app-reviewer");
				if (revs==null){
					apps.getApps().add(app);
					continue;
				}
				if(revs.getRev().size()>=2 || revs.getRev().get(0).getReviewerEmail().equals(email)) continue;
				apps.getApps().add(app);
			}	
		}
		System.out.println(apps.getApps().toString());
		
		return new ModelAndView("hireTeam/reApplicationPage","apps",apps);

	}
	
	@RequestMapping("/hireAppDetail")
	public ModelAndView manAppDetail(HttpSession httpSession, @RequestParam(value="appKey") String appKey) throws IOException{
		String email = (String) httpSession.getAttribute("userEmail");
		Application app = JobManagerApi.getApplicationByKey(appKey);
		ReviewList revs = JobManagerApi.searchReview(email,appKey, "app-reviewer");
		ModelAndView mv = new ModelAndView("hireTeam/reAppDetailPage");
		if (revs!=null){
			mv.addObject("rev",revs.getRev().get(0));
		}else mv.addObject("rev",new Review());
		mv.addObject("app",app);
		return mv;
	}
	
	@RequestMapping("/confirmApp")
	public String createReview(HttpSession httpSession, @RequestParam(value="appKey") String appKey,@RequestParam(value="comment") String comment,@RequestParam(value="decision") String decision ) throws IOException{
		String email = (String) httpSession.getAttribute("userEmail");
		JobManagerApi.createReview(email,appKey,comment,decision);
		return "redirect:/processedApp";
	}
}
