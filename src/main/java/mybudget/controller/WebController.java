package mybudget.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/edituser/{id}")
	public String editUserForm(@PathVariable("id") long id, Model model) 
	{
		User usr = repo.findById(id).orElse(null);
		if(usr == null) return "error"; // error page goes here
		model.addAttribute("user", usr);
		return "edit-user"; 
	}

	@PostMapping("/saveuser")
	public String saveUserForm(@ModelAttribute("user") User user, Model model)
	{
		repo.save(user);
		model.addAttribute("user", user);
		return "index"; 
	}

}