package com.packt.volunteerhoursrecorder.domain;

import java.util.List;

import javax.persistence.*;

import org.hibernate.engine.internal.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private long userId;
	@Column(nullable = false)
	private int totalHours;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	private String role;
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Hours> getHours() {
		return hours;
	}

	public void setHours(List<Hours> hours) {
		this.hours = hours;
	}

	@Column(nullable = false)
	private String email;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore
	private List<Hours> hours;

	public User() {
	}

	public User(int totalHours, String username, String password, String email, String role) {
		this.totalHours = totalHours;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
