package com.employee.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.Employee.model.*;

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, Long> {
	@Query(value = "SELECT id FROM user_addresses WHERE user_id = :id", nativeQuery = true)
	public List<Long> searchByUserGetIds(Long id);
	
	@Query(value = "SELECT * FROM user_addresses WHERE user_id = :id", nativeQuery = true)
	public List<UserAddress> searchByUser(Long id);
	
	@Query(value = "UPDATE user_addresses SET user_id = :newId WHERE user_id = :userId", nativeQuery = true)
	public void updateUserId(Long userId, Long newId);
}
