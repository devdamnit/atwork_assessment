package com.employee.Employee.model;

import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import com.employee.Employee.helper.*;

@Entity
@Table(name = "users")
public class User {
        
        @Id
        @Column(name="id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long ID;
        
        @Column(name="firstName")
        @NotBlank(message = "firstName is mandatory")
        private String firstName;

        @NotBlank(message = "lastName is mandatory")
        @Column(name="lastName")
        private String lastName;
        
        @Column(name="annualSalary")
        @Positive(message = "Salary should be positive")
        private long annualSalary; 
        
        @Column(name="dateOfBirth")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @Past
        private LocalDate dateOfBirth; 
        
        @Column(name="email")
        @Email
        private String email; 
        
        @Column(name = "gender", columnDefinition = "ENUM('MALE', 'FEMALE', 'OTHER')", nullable = false)
        @Enumerated(EnumType.STRING)
        private Gender gender;
        
        @Column(name="mobilePhone")
        @NotBlank(message = "mobilePhone is mandatory")
        @Size(min = 10, max = 10, message = "Mobile Phone should be of length 10")
        private String mobilePhone;
        
        @Column(name = "userType", columnDefinition = "ENUM('EMPLOYEE', 'CONSULTANT')", nullable = false)
        @Enumerated(EnumType.STRING)
        private UserType userType;
        
        @Transient
        @OneToMany(targetEntity = UserAddress.class, cascade = CascadeType.ALL)
        @JoinColumn(name = "userId", referencedColumnName = "ID")
        private Set<UserAddress> userAddresses;
        
        @Transient
        @OneToOne(targetEntity = UserSummary.class, cascade =  CascadeType.ALL)
        @JoinColumn(name = "id", referencedColumnName = "id")
        private UserSummary userSummary;

        public Long getId() {
        	return ID;
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

		public long getAnnualSalary() {
			return annualSalary;
		}

		public void setAnnualSalary(long annualSalary) {
			this.annualSalary = annualSalary;
		}

		public LocalDate getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Gender getGender() {
			return gender;
		}

		public void setGender(Gender gender) {
			this.gender = gender;
		}

		public String getMobilePhone() {
			return mobilePhone;
		}

		public void setMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
		}

		public UserType getUserType() {
			return userType;
		}

		public void setUserType(UserType userType) {
			this.userType = userType;
		} 
		
		public void addAddress(UserAddress addr) {
			this.userAddresses.add(addr);
		}
		
		public void removeAddress(UserAddress addr) {
			this.userAddresses.remove(addr);
		}
		
		public String getFullname() {
			return this.firstName + "," + this.lastName;
		}
}

