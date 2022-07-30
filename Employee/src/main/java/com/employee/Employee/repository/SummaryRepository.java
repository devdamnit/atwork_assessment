package com.employee.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.Employee.helper.*;
import com.employee.Employee.model.*;

@Repository
public interface SummaryRepository extends JpaRepository<UserSummary, User> {
	
	@Query(value = "SELECT * FROM userSummaries WHERE firstName = :name or lastName = :name", nativeQuery = true)
	public List<UserSummary> searchByName(String name);
	
	@Query(value = "SELECT * FROM userSummaries WHERE userType = :#{#type?.name()}", nativeQuery = true)
	public List<UserSummary> searchByName(UserType type);
}
