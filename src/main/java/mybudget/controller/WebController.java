package mybudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import mybudget.repository.ExpensesRepository;

@Controller
public class WebController {

	@Autowired
	ExpensesRepository repo;

}
