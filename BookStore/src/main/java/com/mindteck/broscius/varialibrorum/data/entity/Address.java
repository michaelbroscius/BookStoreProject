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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((stateOrProvince == null) ? 0 : stateOrProvince.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipOrPostal == null) ? 0 : zipOrPostal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (stateOrProvince == null) {
			if (other.stateOrProvince != null)
				return false;
		} else if (!stateOrProvince.equals(other.stateOrProvince))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zipOrPostal == null) {
			if (other.zipOrPostal != null)
				return false;
		} else if (!zipOrPostal.equals(other.zipOrPostal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", stateOrProvince=" + stateOrProvince
				+ ", zipOrPostal=" + zipOrPostal + ", country=" + country + "]";
	}
}
