package mybudget.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mybudget.beans.Expense;
import mybudget.repository.ExpensesRepository;

@Controller
public class WebController {

	@Autowired
	ExpensesRepository repo;
	
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
