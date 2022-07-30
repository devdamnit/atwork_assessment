package com.employee.Employee.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.employee.Employee.helper.*;
import com.employee.Employee.model.*;
import com.employee.Employee.repository.*;
import java.util.List;

@Service
public class EmployeeService {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	AddressRepository addrRepo;
	@Autowired
	SummaryRepository summaryRepo;
	
	// CREATE User
	public User createUser(User user) {
        User savedUser = userRepo.save(user); 
        this.createUserSummary(savedUser);
        return savedUser;
    }
	
	// CREATE UserSummary
	public UserSummary createUserSummary(User user) {
		UserSummary newSummary = new UserSummary();
		newSummary.setID(user);
		newSummary.setDateOfBirth(user.getDateOfBirth());
		newSummary.setFirstName(user.getFirstName());
		newSummary.setLastName(user.getLastName());
		return summaryRepo.save(newSummary);
	}
	
	// READ UserSummaries by name
	public List<UserSummary> getSummaries(String name) {
		if (name != null) {			
			return summaryRepo.searchByName(name);
		}
		else {
			return summaryRepo.findAll();
		}
	}
	
	// READ UserSummaries by UserType
	public List<UserSummary> getSummaries(UserType name) {
		if (name != null) {			
			return summaryRepo.searchByName(name);
		}
		else {
			return summaryRepo.findAll();
		}
	}
	
	// READ User by ID
	public User getUser(Long ID) {
		return userRepo.findById(ID).get();
	}
	
	// DELETE User by ID
	public void deleteUser(Long ID) {
		User userToBeDeleted = userRepo.findById(ID).get();
		summaryRepo.deleteById(userToBeDeleted);
		addrRepo.deleteAllById(addrRepo.searchByUserGetIds(ID));
		userRepo.deleteById(ID);
	}
	
	// UPDATE User's ID
	public User updateUser(Long ID, User newUser) {
		User user = userRepo.findById(ID).get();
		summaryRepo.deleteById(user);
		user.setAnnualSalary(newUser.getAnnualSalary());
		user.setDateOfBirth(newUser.getDateOfBirth());
		user.setEmail(newUser.getEmail());
		user.setFirstName(newUser.getFirstName());
		user.setGender(newUser.getGender());
		user.setLastName(newUser.getLastName());
		user.setMobilePhone(newUser.getMobilePhone());
		user.setUserType(newUser.getUserType());
		User savedUser = userRepo.save(user);
		this.createUserSummary(savedUser);
		return savedUser;
	}
	
	// READ UserAddresses by UserId
	public List<UserAddress> getAddresses(Long id) {
		return addrRepo.searchByUser(id);
	}
	
	// CREATE UserAddress
	public UserAddress createAddress(UserAddress addr, Long ID) {
		UserAddress addy = new UserAddress();
		User user = userRepo.findById(ID).get();
		addy.setUser(user);
		addy.setAddrLn1(addr.getAddrLn1());
		addy.setAddrLn2(addr.getAddrLn2());
		addy.setAddrName(addr.getAddrName());
		addy.setAddrType(addr.getAddrType());
		addy.setCity(addr.getCity());
		addy.setCountry(addr.getCountry());
		addy.setPostalCode(addr.getPostalCode());
		addy.setStateCode(addr.getStateCode());
		UserAddress savedAddr = addrRepo.save(addy);
		user.addAddress(savedAddr);
		return savedAddr;
	}
}
