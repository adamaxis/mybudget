package mybudget.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mybudget.beans.Expense;
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
	
	@GetMapping("/searchForUser")
	public String searchForUser(Model model) 
	{
		return "search-for-user"; 
	}
	
	@RequestMapping(value = "/searchForUser", method = RequestMethod.POST)
	public String saveUserForm(Model model, @RequestParam(value="searchText") String searchText, @RequestParam(value="searchType") String searchType)
	{
		List<User> users = null;
		if(searchType.equals("first_name")) {
			users = repo.findByFirstName(searchText);
		} else if(searchType.equals("last_name")) {
			users = repo.findByLastName(searchText);
		} else if(searchType.equals("email")) {
			users = repo.findByEmail(searchText);
		}
		
		if(users == null) return "error";
		model.addAttribute("users", users);
		return "results"; 
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
	
	@GetMapping("/searchExpense")
	public String searchExpense(Model model) 
	{
		return "search-expense"; 
	}
	
	@RequestMapping(value = "/searchExpense", method = RequestMethod.POST)
	public String saveExpenseForm(Model model, @RequestParam(value="searchText") String searchText, @RequestParam(value="searchType") String searchType)
	{	
		List<Expense> exp = null;
		if(searchType.equals("name")) {
			exp = repo.findByName(searchText);
		} else if(searchType.equals("category")) {
			exp = repo.findByCategory(searchText);
		}
		
		if(exp == null) return "error";
		model.addAttribute("exp", exp);
		return "results"; 
	}
}