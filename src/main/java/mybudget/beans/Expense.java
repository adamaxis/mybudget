package mybudget.beans;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="expense")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long expense_id;
	@DateTimeFormat(pattern="yyyy-MM-dd") // for model verification
	private LocalDate date_time;
	private String name;
	private double amount;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "category_id")
	private Category category;
	
	public Expense() {
		super();
	}
	
	public Expense(LocalDate date_time, String name, double amount, Category category) {
		super();
		this.date_time = date_time;
		this.name = name;
		this.amount = amount;
		this.category = category;
	}

	public long getExpense_id() {
		return expense_id;
	}

	public void setExpense_id(long expense_id) {
		this.expense_id = expense_id;
	}

	public LocalDate getDate_time() {
		return date_time;
	}

	public void setDate_time(LocalDate date_time) {
		this.date_time = date_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Expense [expense_id=" + expense_id + ", date_time=" + date_time + ", name=" + name + ", amount="
				+ amount + ", category=" + category + "]";
	}
	
	
}
