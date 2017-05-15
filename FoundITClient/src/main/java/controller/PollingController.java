package controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import api.PollAndVoteApi;
import dao.PollsDao;
import model.Poll;

@Controller
public class PollingController {
	@RequestMapping("/addPoll")
	public String addPolling(HttpSession httpSession,final RedirectAttributes redirectAttributes,@ModelAttribute("poll") Poll poll) throws IOException{
		System.out.println(poll.toString());
		PollAndVoteApi.createPoll( poll);
		return "redirect:/pollList";
		
	}
	
	@RequestMapping("/deletePoll")
	public String delPolling(HttpSession httpSession, ModelAndView mv, @RequestParam(value="delete") String id) throws IOException{
		System.out.println(id);
		//TODO status check		
		//String email = (String) httpSession.getAttribute("userEmail");
		PollAndVoteApi.deletePoll(id);
		return "welcome";
	
	}
	
	@RequestMapping("/pollList/{id}")
	public ModelAndView pollList(@PathParam("id") String pid) throws IOException{;
		PollsDao polls =  PollAndVoteApi.getPollById(pid);
		return new ModelAndView("PollAndVote/pollResult","polls",polls);
		
	}
	
}
