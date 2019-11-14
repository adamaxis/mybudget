package mybudget.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	//launch results page showing all data
	@GetMapping("/viewAll")
	public String viewAllBudget(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("users", repo.findAll());
		return "results";
	}
	
	
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
	
	// Code for printing model attributes to console -- Daniel
	/*Map<String, Object> y = model.asMap();
    for (Entry<String, Object> entry : y.entrySet())  
        System.out.println("Key = " + entry.getKey() + 
                         ", Value = " + entry.getValue()); */

	//launch for editing expense
	@GetMapping("/editExpense/{id}")
	public String editExpenseForm(@PathVariable("id") long id, Model model) {
		User usr = repo.findById(id).orElse(null);
		if(usr == null) return "error"; // error page goes here
		model.addAttribute("user", usr);
		return "edit-user"; 
	}
	
	@PostMapping("/saveExpense")
	public String saveExpenseForm(@ModelAttribute("user") User user, Model model)
	{
		repo.save(user);
		model.addAttribute("user", user);
		return "results"; 
	}
	
	@GetMapping("/deleteExpense/{id}")
	public String deleteExpenseForm(@PathVariable("id") long id, Model model) {
		User usr = repo.findById(id).orElse(null);
		if(usr == null) return "error"; // error page goes here
		repo.delete(usr);
		model.addAttribute("users", repo.findAll());
		return "results"; 
	}
	
}