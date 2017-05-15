package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import DB.DBOperation;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String welcome() {
		//DBOperation.createUserDB();
		//DBOperation.addUser("2","manager", "nnn", "male", "unsw", "1", "is cool");
		return "welcome";
	}
}
