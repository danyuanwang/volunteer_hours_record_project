package com.packt.volunteerhoursrecorder.domain;

public class UserRegisterForm {
	private String email;
	private String role;
	private String password;
	private String passwordConfirmed;
	private String username;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}
	public void setPasswordConfirmed(String passwordConfirmed) {
		this.passwordConfirmed = passwordConfirmed;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
