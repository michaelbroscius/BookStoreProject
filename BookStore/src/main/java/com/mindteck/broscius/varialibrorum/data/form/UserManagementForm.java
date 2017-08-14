package com.mindteck.broscius.varialibrorum.data.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserManagementForm {
	
	@NotNull
	@Size(max = 100, min = 3)
	private String name;

	@NotNull
	@Size(max = 60, min = 5)
	@Email(message = "Please enter a valid e-mail address.")
	private String email;

	@NotNull
	@Size(min = 1, max = 250)
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
