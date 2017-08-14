package com.mindteck.broscius.varialibrorum.data.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Embedded
	private Address address;

	private String phoneNo;

	public Publisher() {
	}

	public Publisher(String name, Address address, String phoneNo) {
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String lastName) {
		this.name = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", name=" + name + ", address=" + address + ", phoneNo=" + phoneNo + "]";
	}

}
