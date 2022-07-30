package com.employee.Employee.controller;

import javax.validation.Valid;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Employee.helper.*;
import com.employee.Employee.model.*;
import com.employee.Employee.service.EmployeeService;

@RestController
@RequestMapping("/")
public class EmpController {
	@Autowired
    EmployeeService empService;
	
	@RequestMapping(value="users", method=RequestMethod.POST)
    public User createUser(@Valid @RequestBody User user) {
        return empService.createUser(user);
    }
	
	@RequestMapping(value="users/summary", method=RequestMethod.GET)
	public List<UserSummary> readSummaries(@RequestParam("name") String name) {
		return empService.getSummaries(name);
	}
	
	@RequestMapping(value="users/summary", method=RequestMethod.GET)
	public List<UserSummary> readSummaries(@RequestParam("name") UserType name) {
		return empService.getSummaries(name);
	}
	
	@RequestMapping(value="users/{userId}", method=RequestMethod.GET)
    public User readUser(@PathVariable(value = "userId") Long ID) {
        return empService.getUser(ID);
    }
	
	@RequestMapping(value="users/{userId}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value = "userId") Long ID) {
        empService.deleteUser(ID);
    }
	
	@RequestMapping(value="users/{userId}", method=RequestMethod.PUT)
    public User changeUser(@PathVariable(value = "userId") Long ID, @Valid @RequestBody User user) {
        return empService.updateUser(ID, user);
    }
	
	@RequestMapping(value="users/{userId}/address", method=RequestMethod.GET)
	public List<UserAddress> readAddresses(@PathVariable(value = "userId") Long ID) {
		return empService.getAddresses(ID);
	}
	
	@RequestMapping(value="users/{userId}/address", method=RequestMethod.POST)
	public UserAddress createAddress(@PathVariable(value = "userId") Long ID, @Valid @RequestBody UserAddress addr) {
		return empService.createAddress(addr, ID);
	}
}
