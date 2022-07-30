package com.employee.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.Employee.model.*;

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, Long> {
	@Query(value = "SELECT ID FROM userAddresses WHERE userId = :id", nativeQuery = true)
	public List<Long> searchByUserGetIds(Long id);
	
	@Query(value = "SELECT * FROM userAddresses WHERE userId = :id", nativeQuery = true)
	public List<UserAddress> searchByUser(Long id);
	
	@Query(value = "UPDATE userAddresses SET userId = :newId WHERE userId = :userId", nativeQuery = true)
	public void updateUserId(Long userId, Long newId);
}
