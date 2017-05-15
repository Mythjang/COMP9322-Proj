package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import DB.DBOperation;
import api.JobManagerApi;
import dao.AppList;
import dao.JobsDao;
import model.Job;
import model.User;

@Controller
public class ManagerController {
	
	
	@RequestMapping("/jobList")
	public ModelAndView jobList(HttpSession httpSession) throws IOException{
		
		String email = (String) httpSession.getAttribute("userEmail");
		JobsDao jobs =  JobManagerApi.searchJob(email,null);
		return new ModelAndView("manager/manager","jobs",jobs);
		
	}
	
	
	@RequestMapping("/HireTeamList")
	public ModelAndView hireTeamList(HttpSession httpSession, @ModelAttribute("job") Job job) throws IOException, SQLException, ClassNotFoundException{
		
		List<User> hireTeams =  DBOperation.getHireTeam((String) httpSession.getAttribute("userEmail"));
		return new ModelAndView("manager/hireteamPage","hireTeams",hireTeams);
		
	}
	
	
	@RequestMapping("/addJob")
	public String addJob(HttpSession httpSession,final RedirectAttributes redirectAttributes,@ModelAttribute("job") Job job) throws IOException{
		System.out.println(job.toString());
		String email = (String) httpSession.getAttribute("userEmail");
		JobManagerApi.createJob(email, job);
		return "redirect:/jobList";
		
	}
	
	@RequestMapping("/jobDetail")
	public ModelAndView jobDetail(HttpSession httpSession,@RequestParam(value="jobDetail") String id) throws IOException{
		
		Job job =  JobManagerApi.getJobById(id);
		return new ModelAndView("manager/jobDetailPage","job",job);
		
	}
	@RequestMapping("/addHireTeam")
	public String addHireTeam(HttpServletRequest request, HttpSession httpSession,@RequestParam(value="email") String email, @RequestParam(value="password") String password) throws IOException, SQLException, ClassNotFoundException{

			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			System.out.println("aaaaaaaaa"+(String) httpSession.getAttribute("userEmail"));
			user.setWorkFor((String) httpSession.getAttribute("userEmail"));
			user.setType("hireTeam");
			System.out.println(user.toString());
			System.out.println(DBOperation.addUser(user));
		
		
		return "redirect:/HireTeamList";
		
	}@RequestMapping("/deleteJob")
	public String deleteJob(HttpSession httpSession, ModelAndView mv, @RequestParam(value="delete") String id) throws IOException{
		System.out.println(id);
		//TODO status check		
		//String email = (String) httpSession.getAttribute("userEmail");
		
		JobManagerApi.deleteJob(id);
		return "redirect:/jobList";
		
	//return new ModelAndView("generator", "resp", ControllerHelper.getNews(version,startD,endD,inIds,tpCode));
	}
	
	
	@RequestMapping("/createJ")
	public String createJob(HttpSession httpSession, @ModelAttribute("job") Job job) throws IOException{
		return  "manager/createJob";
		
	}

	

	
	@RequestMapping("/detail")
	public ModelAndView detail(HttpSession httpSession, @RequestParam(value="detail") String id) throws IOException{
		httpSession.setAttribute("currId", id);
		return new ModelAndView("manager/applicationPage");
		
	}
	@RequestMapping("/applications")
	public ModelAndView application(HttpSession httpSession) throws IOException{
		String jobId = (String)httpSession.getAttribute("jobId");
		AppList appList = JobManagerApi.searchApps(null, jobId,"app-manager");
		
		return new ModelAndView("manager/applicationPage","appList", appList);
		
	
	}
	@RequestMapping("/hireTeam")
	public String hireTeam(HttpSession httpSession) throws IOException{
		
		return  "redirect:HireTeamList";
		
	
	}
	
	@RequestMapping("/review")
	public String review(HttpSession httpSession) throws IOException{
		
		return  "redirect:manager/reviewPage.jsp";	
	
	}
	
	
	@RequestMapping("/deleteOrChangeJob")
	
	public String deleteOrChangeJob(HttpServletRequest request,@ModelAttribute("job") Job job) throws IOException{
		System.out.println(job.toString());
		if(request.getParameter("action").equals("change")){
			JobManagerApi.updateJob(job);
			return "redirect:/jobDetail?jobDetail="+job.getKey();
		}
		else{
			return "redirect:/deleteJob?delete="+job.getKey();
		
		}
		
	}
	
}
