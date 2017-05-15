package controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import DB.DBOperation;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String welcome() throws SQLException, ClassNotFoundException {
		//DBOperation.createUserDB();
		//DBOperation.addUser("1","manager", "nnn", "male", "unsw", "1", "is cool");
		return "welcome";
	}
}
