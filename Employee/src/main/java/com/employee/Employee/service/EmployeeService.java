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
		newSummary.setId(user.getId());
		newSummary.setDateOfBirth(user.getDateOfBirth());
		newSummary.setFirstName(user.getFirstName());
		newSummary.setLastName(user.getLastName());
		return summaryRepo.save(newSummary);
	}
	
	// READ UserSummaries by name or by UserType
	public List<UserSummary> getSummaries(String name) {
		if (name != null) {
			// query parameter name is not null
			if (name.equals(UserType.EMPLOYEE.name()) || name.equals(UserType.CONSULTANT.name())) {
				// if name matches any UserType
				return summaryRepo.findAllById(userRepo.getIdsByUserType(name));
			}
			else {
				// if name does not match any UserType
				return summaryRepo.searchByName(name);
			}
		}
		else {
			// return all user summaries
			return summaryRepo.findAll();
		}
	}
	
	// READ User by ID
	public User getUser(Long ID) {
		return userRepo.findById(ID).get();
	}
	
	// DELETE User by ID
	public Boolean deleteUser(Long ID) {
		try {
			// first delete the table rows using the foreign key
			summaryRepo.deleteById(ID);
			addrRepo.deleteAllById(addrRepo.searchByUserGetIds(ID));
			// then delete the user
			userRepo.deleteById(ID);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	
	// UPDATE User's ID
	public User updateUser(Long ID, User newUser) {
		User user = userRepo.findById(ID).get();
		summaryRepo.deleteById(ID);
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
		addy.setUserId(user.getId());
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
