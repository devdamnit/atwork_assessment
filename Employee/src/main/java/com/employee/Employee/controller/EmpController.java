package com.employee.Employee.controller;

import javax.validation.Valid; 
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.employee.Employee.model.*;
import com.employee.Employee.service.EmployeeService;
 
@RestController
@RequestMapping("/")
public class EmpController {
	@Autowired
    EmployeeService empService;
	
	@RequestMapping(value="users", method=RequestMethod.POST)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(empService.createUser(user));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request (CODE 400)\n");
        }
    }
	
	@RequestMapping(value="users/summary", method=RequestMethod.GET)
	public ResponseEntity<List<UserSummary>> readSummaries(@RequestParam("name") String name) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(empService.getSummaries(name));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request (CODE 400)\n");
        }
	}
	
	@RequestMapping(value="users/summary", method=RequestMethod.GET)
	public ResponseEntity<List<UserSummary>> readSummaries() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(empService.getSummaries(null));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request (CODE 400)\n");
        }
	}
	
	@RequestMapping(value="users/{userId}", method=RequestMethod.GET)
    public ResponseEntity<?> readUser(@PathVariable(value = "userId") Long ID) {
		try {
			User res = empService.getUser(ID);
			if (res != null) {				
				return ResponseEntity.status(HttpStatus.OK).body(res);
			}
			else {
				return (ResponseEntity<?>) ResponseEntity.noContent();
			}
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request (CODE 400)\n");
        }
    }
	
	@RequestMapping(value="users/{userId}", method=RequestMethod.DELETE)
    public HeadersBuilder<?> deleteUser(@PathVariable(value = "userId") Long ID) {
		if (empService.deleteUser(ID)) {
			return ResponseEntity.noContent();
		}
		else {
			return ResponseEntity.notFound();
		} 
    }
	
	@RequestMapping(value="users/{userId}", method=RequestMethod.PUT)
    public ResponseEntity<?> changeUser(@Valid @RequestBody User user, @PathVariable(value = "userId") Long ID) {
		try {
			User res = empService.updateUser(ID, user);
			if (res != null) {
				ResponseEntity.status(HttpStatus.OK).body(res);
			}
			return (ResponseEntity<?>) ResponseEntity.noContent();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request (CODE 400)\n");
        }
    }
	
	@RequestMapping(value="users/{userId}/address", method=RequestMethod.GET)
	public ResponseEntity<List<UserAddress>> readAddresses(@PathVariable(value = "userId") Long ID) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(empService.getAddresses(ID));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request (CODE 400)\n");
        }
	}
	
	@RequestMapping(value="users/{userId}/address", method=RequestMethod.POST)
	public ResponseEntity<UserAddress> createAddress(@PathVariable(value = "userId") Long ID, @Valid @RequestBody UserAddress addr) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(empService.createAddress(addr, ID));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request (CODE 400)\n");
        }
	}
}
