package controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HireTeamController {
	
	@RequestMapping("/addHireTeam")
	public String addHireTeam(HttpSession httpSession,	HttpServletRequest request) throws IOException{
		Enumeration<String> userInfos = request.getParameterNames();
		while(userInfos.hasMoreElements()){
			System.out.println("username "+request.getParameter(userInfos.nextElement()));
			System.out.println("password "+request.getParameter(userInfos.nextElement()));
		}
		return "redirect:/manager/hireteam";
		
	}
	
}
