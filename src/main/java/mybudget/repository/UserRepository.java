package mybudget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mybudget.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	  @Query("select u from User u where u.first_name = :first_name")
	  List<User> findByFirstName(@Param("first_name") String first_name);
	  @Query("select u from User u where u.last_name = :last_name")
	  List<User> findByLastName(@Param("last_name") String last_name);
	  @Query("select u from User u where u.email = :email")
	  List<User> findByEmail(@Param("email") String email);
}
