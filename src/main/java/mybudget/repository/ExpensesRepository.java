package mybudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mybudget.beans.Expense;

@Repository
public interface ExpensesRepository extends JpaRepository<Expense,Long>{ }
