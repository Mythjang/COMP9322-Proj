package controller;



import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DB.DBOperation;
import api.JobManagerApi;
import dao.JobsDao;
import model.User;


@Controller
public class AuthorityController {
	
	@RequestMapping("/login")
	public ModelAndView login(HttpSession httpSession, @RequestParam(value="email") String email,@RequestParam(value="password") String password) throws IOException{
		System.out.println("login.............");
		System.out.println("email= "+email);
		if(DBOperation.checkLogin(email,password)){
			httpSession.setAttribute("userEmail", email);
			JobsDao jobs =  JobManagerApi.getJobByUser(email);
			return new ModelAndView("manager/manager","jobs",jobs);
		}
			
		return new ModelAndView("welcome", "resp", "inValid username or password");
		
	//return new ModelAndView("generator", "resp", ControllerHelper.getNews(version,startD,endD,inIds,tpCode));
	}
	@RequestMapping("/register")
	public ModelAndView register(@ModelAttribute("user") User user) throws IOException{
	//return new ModelAndView("generator", "resp", ControllerHelper.getNews(version,startD,endD,inIds,tpCode));
	if(!DBOperation.addUser(user)){
		return new ModelAndView("signup", "resp", "Email already exists");
	}
			
	System.out.println(user.toString());
	return new ModelAndView("manager/manager");
	}
	
}