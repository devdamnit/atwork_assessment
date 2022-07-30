package com.employee.Employee.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;


import com.employee.Employee.helper.*;

@Entity
@Table(name = "userAddresses")
public class UserAddress {
	@Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
	@NotEmpty(message = "validation.userId.NotEmpty")
    private User user;
	
	@Column(name="addrLn1")
	@NotBlank(message = "addrLn1 is mandatory")
	private String addrLn1;
	
	@Column(name="addrLn2")
	private String addrLn2;
	
	@Column(name="addrName")
	@NotBlank(message = "addrName is mandatory")
	private String addrName;
	
	@Column(name = "addrType", columnDefinition = "ENUM('BILLING', 'MAIN', 'SHIPPING')", nullable = false)
    @Enumerated(EnumType.STRING)
	private AddrType addrType;
	
	@Column(name="city")
	@NotBlank(message = "city is mandatory")
	private String city;
	
	@Column(name="stateCode")
	@NotBlank(message = "stateCode is mandatory")
	private String stateCode;
	
	@Column(name="postalCode")
	@NotBlank(message = "postalCode is mandatory")
	private String postalCode;
	
	@Column(name="country")
	@NotBlank(message = "country is mandatory")
	private String country;

	public Long getId() {
		return ID;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddrLn1() {
		return addrLn1;
	}

	public void setAddrLn1(String addrLn1) {
		this.addrLn1 = addrLn1;
	}

	public String getAddrLn2() {
		return addrLn2;
	}

	public void setAddrLn2(String addrLn2) {
		this.addrLn2 = addrLn2;
	}

	public String getAddrName() {
		return addrName;
	}

	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}

	public AddrType getAddrType() {
		return addrType;
	}

	public void setAddrType(AddrType addrType) {
		this.addrType = addrType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
