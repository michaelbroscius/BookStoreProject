package com.mindteck.broscius.varialibrorum.data.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String street;
	private String city;
	private String stateOrProvince;
	private String zipOrPostal;
	private String country;

	public Address() {
	}

	public Address(String street, String city, String stateOrProvince, String zipOrPostal, String country) {
		this.street = street;
		this.city = city;
		this.stateOrProvince = stateOrProvince;
		this.zipOrPostal = zipOrPostal;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateOrProvince() {
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	public String getZipOrPostal() {
		return zipOrPostal;
	}

	public void setZipOrPostal(String zipOrPostal) {
		this.zipOrPostal = zipOrPostal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", stateOrProvince=" + stateOrProvince
				+ ", zipOrPostal=" + zipOrPostal + ", country=" + country + "]";
	}
}
