package mybudget.beans;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long user_id;
	private String first_name;
	private String last_name;
	private String email;
	@DateTimeFormat(pattern="yyyy-MM-dd") // for model verification
	private LocalDate birth_date;
	@DateTimeFormat(pattern="yyyy-MM-dd") // for model verification
	private LocalDate creation_date;
	private double budget_amount;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Expense> expenses;
	
	public User() {
		super();
	}

	public User(long user_id, String first_name, String last_name, String email, LocalDate creation_date, LocalDate birth_date, double budget_amount,
			List<Expense> expenses) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.creation_date = creation_date;
		this.birth_date = birth_date;
		this.budget_amount = budget_amount;
		this.expenses = expenses;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(LocalDate date) {
		this.creation_date= date;
	}
	
	public LocalDate getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(LocalDate date) {
		this.birth_date= date;
	}

	public double getBudget_amount() {
		return budget_amount;
	}

	public void setBudget_amount(double budget_amount) {
		this.budget_amount = budget_amount;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	
	public Expense getExpense(int i) {
		return expenses.get(i);
	}
	
	public double getBalance() {
		try {
			Double balance = 0.0, totalExpense = 0.0;
			for(Expense exp : getExpenses()) {
				totalExpense += exp.getAmount();
			}
			balance =  budget_amount - totalExpense;
			if(balance.isInfinite() || balance.isNaN()) return 0;
			return balance;
		} catch (NullPointerException ex) {
			return 0;
		}
	}
	
	public double getPercent() {
			double available = getBalance();
			Double percent = (available / budget_amount) * 100;
		if(percent.isInfinite() || percent.isNaN()) return 0;
		return percent;
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", email="
				+ email + ", birth_date=" + birth_date + ", creation_date=" + creation_date + ", budget_amount=" + budget_amount + ", expenses=" + expenses + "]";
	}
	
}