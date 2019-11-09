package mybudget.beans;

import java.util.List;

public class Budget {
	
	private List<Expense> expenses;

	public Budget() {
		super();
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public void addExpense(Expense expense) {
		this.expenses.add(expense);
	}
}
