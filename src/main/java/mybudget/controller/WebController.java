package mybudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mybudget.repository.UserRepository;

@Controller
public class WebController {

	@Autowired
	UserRepository repo;
	
	@GetMapping("/addBudget")
	public String addBudgetForm(Model model) 
	{
		
		return "create-budget"; 
	}

}
