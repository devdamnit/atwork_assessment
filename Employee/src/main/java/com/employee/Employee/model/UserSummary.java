package com.employee.Employee.model;

import java.time.LocalDate;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "userSummaries")
public class UserSummary {
	@Id
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userSummary", nullable = false)
    private User ID;
	
    @Column(name="dateOfBirth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate dateOfBirth; 
    
    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

	public User getID() {
		return ID;
	}

	public void setID(User ID) {
		this.ID = ID;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
