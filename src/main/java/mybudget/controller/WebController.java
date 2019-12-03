package mybudget.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import mybudget.beans.User;
import mybudget.repository.UserRepository;

@Controller
public class WebController {

	@Autowired
	UserRepository repo;

	// launch results page showing all data
	@GetMapping("/viewAll")
	public String viewAllBudget(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("users", repo.findAll());
		return "results";
	}

	@GetMapping("/addBudget")
	public String addBudgetForm(@ModelAttribute("user") User user) {

		return "create-budget";
	}

	@PostMapping("/saveBudget")
	public String saveUserBudget(@ModelAttribute("user") User user, Model model) {
		user.setCreation_date(LocalDate.now());
		repo.save(user);
		model.addAttribute("users", repo.findAll());
		return "results";
	}
	
	@PostMapping("/updateBudget/{id}")
	public String updateUserBudget(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			 user.setUser_id(id);
			 System.out.println("ERROR");
			 return "view-edit-budget";
		}
		user.setUser_id(id);
		
		repo.save(user);
		model.addAttribute("users", repo.findAll());
		
		return "view-edit-budget";
	}

	@GetMapping("/editBudget/{id}")
	public String viewEditBudgetForm(@PathVariable("id") long id, Model model) {
		User user = repo.findById(id).orElse(null);
		if (user == null)
			return "error";
		model.addAttribute("user", user);
		return "view-edit-budget";
	}
	
	@PostMapping("/saveUser")
	public String saveUserForm(@ModelAttribute("user") User user, Model model) {
		repo.save(user);
		model.addAttribute("user", user);
		return "index";
	}
	
	@GetMapping("/editUser/{id}")
	public String editUserForm(@PathVariable("id") long id, Model model) {
		User usr = repo.findById(id).orElse(null);
		if(usr == null) return "error"; 
		
		model.addAttribute("user", usr);
		return "edit-user";
	}	
	
	@GetMapping("/searchForUser")
	public String searchForUser(Model model) {
		return "search-for-user";
	}

	@RequestMapping(value = "/searchForUser", method = RequestMethod.POST)
	public String saveUserForm(Model model, @RequestParam(value = "searchText") String searchText,
			@RequestParam(value = "searchType") String searchType) {
		List<User> users = null;
		if (searchType.equals("first_name")) {
			users = repo.findByFirstName(searchText);
		} else if (searchType.equals("last_name")) {
			users = repo.findByLastName(searchText);
		} else if (searchType.equals("email")) {
			users = repo.findByEmail(searchText);
		}

		if (users == null)
			return "error";
		model.addAttribute("users", users);
		return "results";
	}
	
	@GetMapping("/deleteBudget/{id}")
	public String deleteBudgetForm(@PathVariable("id") long id, Model model) {
		User user = repo.findById(id).orElse(null);
		if (user == null)
			return "error"; // error page goes here
		repo.delete(user);
		model.addAttribute("users", repo.findAll());
		return "results";
	}

	@GetMapping("/searchExpense")
	public String searchExpense(Model model) {
		return "search-expense";
	}

	@RequestMapping(value = "/searchExpense", method = RequestMethod.POST)
	public String saveExpenseForm(Model model, @RequestParam(value = "searchText") String searchText,
			@RequestParam(value = "searchType") String searchType) {
		List<User> exp = repo.findAll();
		for (int i = exp.size() - 1; i >= 0; i--) {
			boolean found = false;
			for (int j = 0; j < exp.get(i).getExpenses().size(); j++) {
				if (searchType.equals("name")) {
					if (exp.get(i).getExpense(j).getName().equals(searchText))
						found = true;
				} else if (searchType.equals("category")) {
					if (exp.get(i).getExpense(j).getCategory().getName().equals(searchText))
						found = true;
				}
				if (found)
					break;
			}
			if (!found) {
				exp.remove(i);
			}
		}
		if (exp == null)
			return "error";
		model.addAttribute("users", exp);
		return "results";
	}
	
	// Code for printing model attributes to console -- Daniel
		/*
		 * Map<String, Object> y = model.asMap(); for (Entry<String, Object> entry :
		 * y.entrySet()) System.out.println("Key = " + entry.getKey() + ", Value = " +
		 * entry.getValue());
		 */
}