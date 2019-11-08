package mybudget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import mybudget.repository.ExpensesRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "mybudget.repository.**" })
@EntityScan(basePackages = { "mybudget.beans.**" })
@ComponentScan(basePackages = { "mybudget.**" })
@EnableAutoConfiguration
public class MyBudgetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBudgetApplication.class, args);
	}

	@Autowired
	ExpensesRepository repo;
}
