package com.packt.volunteerhoursrecorder.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class hours {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int time;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	private String user;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	

}
