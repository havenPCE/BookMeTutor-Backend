package com.pce.BookMeTutor.Model.Dto.Responses;

import java.util.Date;
import java.util.List;

public class TutorDetailsResponse {

	private String email;
	private String first_name;
	private String last_name;
	private String gender;
	private String qualification;
	private String screening = "pending";
	private String line1;
	private String line2;
	private String city;
	private String pincode;
	private Date lastSelected;
	private List<BookingResponse> bookingResponses;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getScreening() {
		return screening;
	}
	public void setScreening(String screening) {
		this.screening = screening;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Date getLastSelected() {
		return lastSelected;
	}
	public void setLastSelected(Date lastSelected) {
		this.lastSelected = lastSelected;
	}
	public List<BookingResponse> getBookingResponses() {
		return bookingResponses;
	}
	public void setBookingResponses(List<BookingResponse> bookingResponses) {
		this.bookingResponses = bookingResponses;
	}
	public TutorDetailsResponse(String email, String first_name,
			String last_name, String gender, String qualification,
			String screening, String line1, String line2, String city,
			String pincode, Date lastSelected,
			List<BookingResponse> bookingResponses) {
		super();
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.qualification = qualification;
		this.screening = screening;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.pincode = pincode;
		this.lastSelected = lastSelected;
		this.bookingResponses = bookingResponses;
	}
	public TutorDetailsResponse() {
		// TODO Auto-generated constructor stub
	}

}
