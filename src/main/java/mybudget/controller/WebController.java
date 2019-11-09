package mybudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mybudget.beans.Budget;
import mybudget.beans.Expense;
import mybudget.repository.ExpensesRepository;

@Controller
public class WebController {

	@Autowired
	ExpensesRepository repo;
	
	@GetMapping("/addBudget")
	public String addBudgetForm(Model model) 
	{
		Budget budget = new Budget();
		model.addAttribute("budget", budget);
		return "create-budget"; 
	}
	
	@PostMapping("/saveBudget")
	public String addExpensesToDb(@ModelAttribute("budget") Budget budget , Model model) { 
		Expense expense = new Expense();
		for(int i=0; i < budget.getExpenses().size(); i++) {
			expense = budget.getExpenses().get(i);
			if(expense != null && expense.getAmount() != 0) {
				repo.save(expense);
			}
		}
		
		model.addAttribute("expenses", repo.findAll());  
		return "results";
	}

}
