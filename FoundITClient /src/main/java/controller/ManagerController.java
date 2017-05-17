package controller;

import DB.DBOperation;
import api.JobManagerApi;
import api.MailService;
import dao.AppList;
import dao.JobsDao;
import model.Application;
import model.Job;
import model.PostModel;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class ManagerController {

	@Autowired
	private MailService mailService;

	@RequestMapping("/jobList")
	public ModelAndView jobList(HttpSession httpSession) throws IOException{

		String email = (String) httpSession.getAttribute("userEmail");
		JobsDao jobs =  JobManagerApi.searchJobByUserOrStatus(email,null);
		return new ModelAndView("manager/manager","jobs",jobs);

	}


	@RequestMapping("/HireTeamList")
	public ModelAndView hireTeamList(HttpSession httpSession, @ModelAttribute("job") Job job) throws IOException, SQLException, ClassNotFoundException{

		List<User> hireTeams =  DBOperation.getHireTeam((String) httpSession.getAttribute("userEmail"));
		//System.out.println(hireTeams.size());
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
		AppList appList = JobManagerApi.searchApps(null, id,"app-manager");
		return new ModelAndView("manager/applicationPage","appList", appList);
	}

	@RequestMapping("/applications")
	public ModelAndView application(HttpSession httpSession) throws IOException{
		String jobId = (String)httpSession.getAttribute("currId");
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
	@RequestMapping("/manAppDetail")
	public ModelAndView manAppDetail(@RequestParam(value="appKey") String appKey) throws IOException{
		Application app = JobManagerApi.getApplicationByKey(appKey);
		return new ModelAndView("manager/manAppDetailPage","app",app);
	}




	@RequestMapping("/passedApplication")
	public ModelAndView getPassApp(HttpSession httpSession) throws IOException{
		String jobId = (String)httpSession.getAttribute("currId");
		AppList apps = JobManagerApi.getPassedApplication(jobId);
		return new ModelAndView("manager/pApplicationPage","appList",apps);
	}


	@RequestMapping(value = "/vote")
	public String doVote(HttpSession httpSession, @RequestParam String title, @RequestParam List<String> option) {
		String url = "http://localhost:8000/poll";

		PostModel postModel = new PostModel();
		postModel.setTitle(title);
		postModel.setOptions(option);
		System.out.println(postModel.toString());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PostModel> entity = new HttpEntity<PostModel>(postModel, headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
//		String shortLink = restTemplate.postForObject(url, postModel, String.class);
		System.out.println(responseEntity.getBody());
		String jobId = (String)httpSession.getAttribute("currId");
		AppList apps = JobManagerApi.getPassedApplication(jobId);
		String email = "yaoyu5507203@gmail.com";

		//for (Application app : apps.getApps()) {
			mailService.sendMail(email, "Poll", responseEntity.getBody());
		//}

		return "redirect:/jobList";
	}

	@RequestMapping("/pollAndVote")
	public ModelAndView pollPage() throws IOException{
		return new ModelAndView("manager/pollPage");
	}
	
	
//	@RequestMapping("/passedApp")
//	public ModelAndView getPassApp(HttpSession httpSession) throws IOException{
//		String jobId = (String)httpSession.getAttribute("currId");
//		AppList apps = JobManagerApi.getPassedApplication(jobId);
//		return new ModelAndView("manager/manAppDetailPage","app",apps);
//	}
}
