package com.mindteck.broscius.varialibrorum.data.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class LoginForm {
	@NotNull(message = "May not be blank.")
	@Size(max = 60)
	@Email(message = "Please enter a valid e-mail address.")
	private String email;

	@NotNull(message = "May not be blank.")
	@Size(min = 1, max = 250)
	private String password;

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

	@Override
	public String toString() {
		return "LoginForm [email=" + email + ", password==null: " + (password==null) + "]";
	}

}
