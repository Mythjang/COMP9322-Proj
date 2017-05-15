package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DB.DBOperation;
import api.JobManagerApi;
import dao.AppList;
import dao.JobsDao;
import model.Application;
import model.Job;
import model.User;

@Controller
public class CandidateController {
	
	@RequestMapping("/availableJob")
	public ModelAndView jobList() throws IOException, ClassNotFoundException, SQLException{
		JobsDao jobs = JobManagerApi.searchJob(null,"open");
		JobsDao jobs2 = JobManagerApi.searchJob(null,"appReceived");
		jobs.getJobs().addAll(jobs2.getJobs());
		return new ModelAndView("candidates/candJobList","jobs",jobs);

	}
	
	@RequestMapping("/showDetail")
	public ModelAndView showDetail(HttpSession httpSession) throws IOException, ClassNotFoundException, SQLException{
		String email= (String) httpSession.getAttribute("userEmail");
		User user = DBOperation.getUserDetail(email);
		return new ModelAndView("candidates/userDetailPage","user",user);
		
	}
	
	@RequestMapping("/updateDetail")
	public String updateDetail(HttpSession httpSession, @ModelAttribute User user) throws IOException, ClassNotFoundException, SQLException{
		String email= (String) httpSession.getAttribute("userEmail");
		System.out.println(email);
		DBOperation.updateUserDetail(email, user);
		return "redirect:/showDetail";
		
	}
	
	
	@RequestMapping("/canJobDetail")
	public ModelAndView jobDetail(HttpSession httpSession,@RequestParam(value="jobDetail") String id) throws IOException{
		
		Job job =  JobManagerApi.getJobById(id);
		return new ModelAndView("candidates/candidatesJobDetailPage","job",job);
		
	}
	
	@RequestMapping("/canAppDetail")
	public ModelAndView canAppDetail(HttpSession httpSession,@RequestParam(value="appKey") String appKey,@RequestParam(value="jobId") String jobId) throws IOException{
		
		Job job =  JobManagerApi.getJobById(jobId);
		Application app = JobManagerApi.getApplicationByKey(appKey);
		ModelAndView mv= new ModelAndView("candidates/canAppDetailPage");
		mv.addObject("job", job);
		mv.addObject("app", app);
		return mv;
		
	}
	
	@RequestMapping("/applyJob")
	public String applyJob(HttpSession httpSession,@RequestParam(value="key") String id,@RequestParam(value="coverLetter") String coverLetter ) throws IOException, ClassNotFoundException, SQLException{
		String email= (String) httpSession.getAttribute("userEmail");
		User user = DBOperation.getUserDetail(email);
		Application application = new Application();
		application.setJobId(id);
		application.setCoverLetter(coverLetter);
		application.setEmail(user.getEmail());
		application.setCurrentPosition(user.getCurrentPosition());
		application.setDob(user.getDob());
		application.setEducation(user.getEducation());
		application.setPastExperience(user.getPastExperience());
		application.setPastExperience(user.getProfessionalSkills());
		application.setName(user.getName());
		application.setTitle(user.getTitle());
		JobManagerApi.createApplication(application);
		return "redirect:/availableJob";	
	}
	
	
	@RequestMapping("/myApplications")
	public ModelAndView myApplications(HttpSession httpSession) throws IOException, ClassNotFoundException, SQLException{
		String email= (String) httpSession.getAttribute("userEmail");
		AppList apps = JobManagerApi.searchApps(email, null,"app-candidate");
		return new ModelAndView("candidates/canApplicationPage","apps",apps);
	}
	
	
	@RequestMapping("/updateApp")
	public String updateApplication(@RequestParam(value="coverLetter") String coverLetter,@RequestParam(value="appKey") String appKey,@RequestParam(value="jobId") String jobId) throws IOException, ClassNotFoundException, SQLException{
		System.out.println("__________________________________________________");
		JobManagerApi.updateApp(coverLetter, appKey);
		return "redirect:/canAppDetail?appKey="+appKey+"&jobId="+jobId;
	}
}
