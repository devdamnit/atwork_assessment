package com.employee.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.Employee.model.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT id FROM users WHERE user_type = :type", nativeQuery = true)
	public List<Long> getIdsByUserType(String type);
}
