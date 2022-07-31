package com.employee.Employee.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.employee.Employee.model.*;

@Repository
public interface SummaryRepository extends JpaRepository<UserSummary, Long> {
	
	@Query(value = "SELECT * FROM user_summaries WHERE first_name = :name or last_name = :name", nativeQuery = true)
	public List<UserSummary> searchByName(String name);
}
