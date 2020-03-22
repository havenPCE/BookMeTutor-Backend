package com.pce.BookMeTutor.Model.Dto.Responses;

import java.util.Date;

public class RegistrationResponse {

	private String email;
	private String token;
	private Date registered;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegistered() {
		return registered;
	}
	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public RegistrationResponse(String email, String token, Date registered) {
		super();
		this.email = email;
		this.token = token;
		this.registered = registered;
	}
	public RegistrationResponse() {
		super();
	}

}
