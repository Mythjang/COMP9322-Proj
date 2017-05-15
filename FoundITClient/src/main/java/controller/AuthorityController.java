package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import DB.DBOperation;
import model.User;


@Controller
public class AuthorityController {
	
	@RequestMapping(value = "/login")
	public String login(RedirectAttributes redirectAttributes, HttpSession httpSession, @RequestParam(value="email") String email,@RequestParam(value="password") String password) throws IOException, SQLException, ClassNotFoundException{
		System.out.println("login.............");
		System.out.println("email= "+email);
		String type = DBOperation.checkLogin(email,password);

		if(type != null){
			httpSession.setAttribute("userEmail", email);
			return logHelper(email,type);
		}
		redirectAttributes.addFlashAttribute("resp", "invalid username or password");
		return "redirect:/invalid";
				//new ModelAndView("welcome","resp","invalid username or password");
		
	//return new ModelAndView("generator", "resp", ControllerHelper.getNews(version,startD,endD,inIds,tpCode));
	}
	@RequestMapping("/signup")
	public String signUp(){
		
		return "signup";
	}
	
	@RequestMapping("/register")
	public String register(RedirectAttributes redirectAttributes,HttpSession httpSession,@ModelAttribute("user") User user) throws IOException, SQLException, ClassNotFoundException{
	//return new ModelAndView("generator", "resp", ControllerHelper.getNews(version,startD,endD,inIds,tpCode));
	if(!DBOperation.addUser(user)){
		redirectAttributes.addFlashAttribute("resp", "Email already exists");
		return "redirect:/exists";
	}
			
	System.out.println(user.toString());
	httpSession.setAttribute("userEmail", user.getEmail());
	return logHelper(user.getEmail(), user.getType());
	}
	
	private String logHelper(String email, String type){
		if(type.equals("manager")) {	
			return "redirect:/jobList";
		}
		else if(type.equals("candidate")){
			return "redirect:/availableJob";
		}
		return null;	
	}
	
	@RequestMapping(value="/invalid")
	public String invalidPassword()  {
	    return "welcome";
	}
	
	@RequestMapping(value="/exists")
	public String emailExists()  {
	    return "signup";
	}
}