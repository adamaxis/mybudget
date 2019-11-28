package mybudget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mybudget.beans.Expense;
import mybudget.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	  @Query("select u from User u where u.first_name = :first_name")
	  List<User> findByFirstName(@Param("first_name") String first_name);
	  @Query("select u from User u where u.last_name = :last_name")
	  List<User> findByLastName(@Param("last_name") String last_name);
	  @Query("select u from User u where u.email = :email")
	  List<User> findByEmail(@Param("email") String email);
	  
	  @Query("select e from Expense e where e.expense_id = :expense_id")
	  List<Expense> findByExpenseId(@Param("expense_id") Long expense_id);
	  @Query("select e from Expense e where e.name = :name")
	  List<Expense> findByName(@Param("name") String name);
	  @Query("select e from Expense e where e.category = :category")
	  List<Expense> findByCategory(@Param("category") String category);

}
