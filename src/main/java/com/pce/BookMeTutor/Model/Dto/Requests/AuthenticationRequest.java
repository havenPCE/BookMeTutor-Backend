package com.pce.BookMeTutor.Model.Dto.Requests;

public class AuthenticationRequest {

	private String email;
	private String password;
	private String role;

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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public AuthenticationRequest(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public AuthenticationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
