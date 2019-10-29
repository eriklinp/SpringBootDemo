package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
	
	@GetMapping("/customers/{id}")
	public String findCustomer(@PathVariable Long id, Model model) {
		Customer c  = customerRespository.findById(id);
		if(c == null) throw new CustomerNotFoundException();
		model.addAllAttributes("customer", c);
		return "view_customer";
	}
	@ExceptionHandler(CustomerNotFoundException.class)
	public ModelAndView handleCustomerNotFoundException(CustomerNotFoundException ex) {
		ModelAndView model = new ModelAndView("error/404");
		model.addObject("exception", ex);
		return model;
	}

}
