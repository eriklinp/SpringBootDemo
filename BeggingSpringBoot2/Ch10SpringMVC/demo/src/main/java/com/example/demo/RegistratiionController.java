package com.example.demo;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

@Controller
public class RegistratiionController {
	@Autowired
	private MessageSource messageSource;
	
	public String handleRegistration(User user) {
		
		String code = "email.exists";
		Object[] args = new Object[] {email};
		String defaultMsg = "Email " + email + "already in use";
		
		Locale locale = Locale.getDefault();
		String errorMsg = messageSource.getMessage(code,  args, defaultMsg, locale);
	}
	
	
}
