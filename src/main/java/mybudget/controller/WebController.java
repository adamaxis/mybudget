package mybudget.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mybudget.beans.Expense;
import mybudget.beans.User;
import mybudget.repository.ExpensesRepository;
import mybudget.repository.UserRepository;

@Controller
public class WebController {

	@Autowired
	ExpensesRepository repo;
	@Autowired
	UserRepository repo2;
	
	@GetMapping("/viewAll")
	public String viewAllUsers(Model model) {
		model.addAttribute("users", repo2.findAll());
		return "results";
	}
	
	@GetMapping("/addUser")
	public String addNewUser(Model model) {
		User u = new User();
		model.addAttribute("newUser", u);
		return "addUser";
	}
	
	@PostMapping("/addUser")
	public String addNewUser(@ModelAttribute User u, Model model) {
		repo2.save(u);
		model.addAttribute("users", repo2.findAll());
		return "results";
	}
	
	
	
	@GetMapping("/addBudget")
	public String addBudgetForm(@ModelAttribute("expenses") ArrayList<Expense> expenses) 
	{
		return "create-budget"; 
	}
	
	@PostMapping("/addExpenses")
	public String addExpensesToDb(@ModelAttribute("expenses") ArrayList<Expense> expenses, Model model) { 
		repo.saveAll(expenses);
		model.addAttribute("expenses", repo.findAll());  
		return "results";
	}

}
