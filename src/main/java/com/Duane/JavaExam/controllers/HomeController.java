package com.Duane.JavaExam.controllers;



import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Duane.JavaExam.models.Event;
import com.Duane.JavaExam.models.Join;
import com.Duane.JavaExam.models.User;
import com.Duane.JavaExam.services.AppService;
import com.Duane.JavaExam.validators.UserValidator;

@Controller
public class HomeController {

	private final AppService appServ;
	private final UserValidator userValidator;

	public HomeController(AppService appServ, UserValidator userValidator) {
		this.appServ = appServ;
		this.userValidator = userValidator;
	}
	
//	Login Registration**************************************************
	@PostMapping("registration")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "Index.jsp";
		}
		User u = this.appServ.registerUser(user);
		session.setAttribute("UserId", u.getId());
		return "redirect:/dashboard";
	}

	@PostMapping("/login")
	public String login(@Valid @RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Boolean isLegit = this.appServ.authenticateUser(email, password);

		if (isLegit) {
			User user = this.appServ.findByEmail(email);
			session.setAttribute("UserId", user.getId());
			return "redirect:/userinfo";
		}

		redirectAttributes.addFlashAttribute("error", "Invalid login");
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logour(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
//	End Login Registration**************************************************

	@GetMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "Index.jsp";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session, @ModelAttribute("newEvent") Event question) {
		Long id = (Long) session.getAttribute("UserId");
		User loggedinuser = this.appServ.findUserById(id);
		model.addAttribute("loggedinuser", loggedinuser);
		model.addAttribute("allEvents", appServ.allEvents());
		return "Dashboard.jsp";
	}
	
	@GetMapping("/newEvent")
	public String newIdea(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("UserId");
		User loggedinuser = this.appServ.findUserById(id);
		model.addAttribute("loggedinuser", loggedinuser);
		model.addAttribute("newEvent", new Event());
		return "NewIdea.jsp";
	}
	
	@PostMapping("/createEvent")
	public String createEvent(@Valid @ModelAttribute("newEvent") Event event,  BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors() ) {
			return "NewIdea.jsp";
		}
		else {
			Long id = (Long) session.getAttribute("UserId");
			User loggedinuser = this.appServ.findUserById(id);
			event.setHost(loggedinuser);
			appServ.createEvent(event);
			return "redirect:/dashboard";
		}
	}
//  User Routes*******************************************
	@GetMapping("/userinfo")
	public String userInfo(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("UserId");
		User loggedinuser = this.appServ.findUserById(id);
		model.addAttribute("loggedinuser", loggedinuser);
		return "UserInfo.jsp";
	}
//	End User Routes**********************************
//  ****************Begin Event Routes***************
	
	
	
	@GetMapping("/edit/{id}")
	public String editEvent(@PathVariable("id")Long id, Model model, HttpSession session) {
		Long a = (Long) session.getAttribute("UserId");
		User loggedinuser = this.appServ.findUserById(a);
		model.addAttribute("loggedinuser", loggedinuser);
		model.addAttribute("event", appServ.findEById(id)); 
		model.addAttribute("allEvents", appServ.allEvents());
		return "EditEvent.jsp"; 
	}
	
	@PostMapping("/events/{id}/edit")
	public String updateEvent(@PathVariable("id") Long id, Model model, @Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("event", appServ.findEById(id)); 
			return "ViewOneEvent.jsp";
		}
		Long a = (Long) session.getAttribute("UserId");
		User loggedinuser = this.appServ.findUserById(a);
		event.setHost(loggedinuser);
		appServ.createEvent(event); 
		return "redirect:/dashboard/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEvent(@PathVariable("id") Long id) {
		this.appServ.deleteEvent(id);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/view/{id}")
	public String viewOneEvent(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long a = (Long) session.getAttribute("UserId");
		User loggedinuser = this.appServ.findUserById(a);
		model.addAttribute("loggedinuser", loggedinuser);
    	model.addAttribute("thisEvent", this.appServ.findEById(id));
    	
		return "ViewOneEvent.jsp";
	}
	@GetMapping("/join/{id}")
	public String joinOneEvent(@PathVariable("id") Long id, @ModelAttribute("newEvent") Event event, Model model, HttpSession session) {
		Event E = this.appServ.findEById(id);
		Long a = (Long) session.getAttribute("UserId");
		User loggedinuser = this.appServ.findUserById(a);
    	model.addAttribute("thisEvent", this.appServ.findEById(id));
    	
    	if(E.getAttendees().contains(loggedinuser)) {
    		return "redirect:/dashboard";
    	}
    	Join userEvent = new Join(loggedinuser, E);
    	
    	this.appServ.createJoin(userEvent);
    	
		return "redirect:/dashboard";
    	
	}
	
	@GetMapping("/leave/{id}")
	public String leaveOneEvent(@PathVariable("id") Long id, @ModelAttribute("newEvent") Event event, Model model, HttpSession session) {
		Event e = this.appServ.findEById(id);
		Long a = (Long) session.getAttribute("UserId");
		User loggedinuser = this.appServ.findUserById(a);
    	model.addAttribute("thisEvent", this.appServ.findEById(id));
    	
    	if(!e.getAttendees().contains(loggedinuser)) {
    		return "redirect:/dashboard";
    	}
    	loggedinuser.getAttending().remove(e);
    	this.appServ.leaveJoin(loggedinuser);
		return "redirect:/dashboard";
    	
	}
	
//	End Event Routes**********************************
	
	@GetMapping("/search")
	public String searchEvent(@RequestParam("host")String host, Model model) {
	 	List<Event> event = appServ.searchBy(host);
//	 	model.addAttribute("allEvents", appServ.allEvents());
	 	model.addAttribute("events", event);
	 	model.addAttribute("host", host);
	 	
	 	System.out.println("**************");
	 	return "SpecificHost.jsp";
	}
	

};
