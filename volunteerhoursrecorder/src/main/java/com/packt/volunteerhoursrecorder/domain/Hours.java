package com.packt.volunteerhoursrecorder.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Hours {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private int durationHours;
	private String confirmationEmail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(int durationHours) {
		this.durationHours = durationHours;
	}

	public String getConfirmationEmail() {
		return confirmationEmail;
	}

	public void setConfirmationEmail(String confirmationEmail) {
		this.confirmationEmail = confirmationEmail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hours() {
	}

	public Hours(int durationHours, String confirmationEmail, Date date, User user) {
		super();
		this.durationHours = durationHours;
		this.confirmationEmail = confirmationEmail;
		this.date = date;
		this.user = user;
	}
}
