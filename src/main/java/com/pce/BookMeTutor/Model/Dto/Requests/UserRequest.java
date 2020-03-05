package com.pce.BookMeTutor.Model.Dto.Requests;

import java.util.Set;

import javax.validation.constraints.Email;

public class UserRequest {
	
	@Email
	private String email;
	private String password;
	private String first_name;
	private String last_name;
	private String gender;
	private Set<String> phone;
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
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Set<String> getPhone() {
		return phone;
	}
	public void setPhone(Set<String> phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "UserRequest [email=" + email + ", password=" + password + ", first_name=" + first_name + ", last_name="
				+ last_name + ", gender=" + gender + ", phone=" + phone + "]";
	}
	public UserRequest(@Email String email, String password, String first_name, String last_name, String gender,
			Set<String> phone) {
		super();
		this.email = email;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.phone = phone;
	}
	public UserRequest() {
		super();
	}
	
	
	
	
	
}
