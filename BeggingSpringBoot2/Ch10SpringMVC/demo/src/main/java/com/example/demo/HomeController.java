package com.example.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class HomeController {

//	@RequestMapping(value="/home", method=RequestMethod.GET)
//	public String home(Model model) {
//		model.addAttribute("message", "Hello Spring MVC!");
//		return "home";
//	}
	
//	@PostMapping("/users")
//	public String createUser(User user) {
//		userRepository.save(user);
//		return "users";
//	}
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("message", "SpringBoot + Thymeleaf rocks");
		return "index";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user, Model model) {
		System.out.println("Login User: "+user);
		return "redirect:/";
	}
}
