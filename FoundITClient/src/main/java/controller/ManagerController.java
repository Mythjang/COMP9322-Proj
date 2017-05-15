package controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import api.JobManagerApi;
import dao.JobsDao;
import model.Job;

@Controller
public class ManagerController {
	@RequestMapping("/delete")
	public String login(HttpSession httpSession, ModelAndView mv, @RequestParam(value="delete") String id) throws IOException{
		System.out.println(id);
		//TODO status check		
		//String email = (String) httpSession.getAttribute("userEmail");
		JobManagerApi.deleteJob(id);
		return "redirect:/jobList";
	
	}
	@RequestMapping("/addJob")
	public String addJob(HttpSession httpSession,final RedirectAttributes redirectAttributes,@ModelAttribute("job") Job job) throws IOException{
		System.out.println(job.toString());
		String email = (String) httpSession.getAttribute("userEmail");
		JobManagerApi.createJob(email, job);
		return "redirect:/jobList";
		
	}
	
	@RequestMapping("/createJ")
	public String createJob(HttpSession httpSession) throws IOException{
		return  "manager/createJob";
		
	}
	
	@RequestMapping("/jobList")
	public ModelAndView jobList(HttpSession httpSession) throws IOException{
		
		String email = (String) httpSession.getAttribute("userEmail");
		JobsDao jobs =  JobManagerApi.getJobByUser(email);
		return new ModelAndView("manager/manager","jobs",jobs);
		
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(HttpSession httpSession,  @RequestParam(value="detail") String id) throws IOException{
		
		return new ModelAndView("manager/application");
		
	}
}
