package mybudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mybudget.beans.User;
import mybudget.repository.UserRepository;

@Controller
public class WebController {

	@Autowired
	UserRepository repo;
	
	@GetMapping("/addbudget")
	public String addBudgetForm(@ModelAttribute("user") User user) 
	{
		
		return "create-budget"; 
	}
	
	@PostMapping("/savebudget")
	public String saveUserBudget(@ModelAttribute("user") User user, Model model) { 
		repo.save(user);
		model.addAttribute("user", repo.findAll());  
		return "results";
	}

}
