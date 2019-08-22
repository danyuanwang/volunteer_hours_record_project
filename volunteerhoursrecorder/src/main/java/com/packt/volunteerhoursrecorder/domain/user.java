package com.packt.volunteerhoursrecorder.domain;


import javax.persistence.*;

@Entity
public class user {

	public int getTotal_hours() {
		return total_hours;
	}
	public void setTotal_hours(int total_hours) {
		this.total_hours = total_hours;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int total_hours;
	private String username, password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
