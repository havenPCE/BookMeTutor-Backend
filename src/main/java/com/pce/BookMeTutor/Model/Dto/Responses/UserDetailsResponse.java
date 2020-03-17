package com.pce.BookMeTutor.Model.Dto.Responses;

import java.util.List;

public class UserDetailsResponse {
	private String email;
	private String first_name;
	private String last_name;
	private String gender;
	private List<AddressResponse> addressResponses;
	private List<BookingResponse> bookingResponses;

	public UserDetailsResponse() {
		// TODO Auto-generated constructor stub
	}

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

	public List<AddressResponse> getAddressResponses() {
		return addressResponses;
	}

	public void setAddressResponses(List<AddressResponse> addressResponses) {
		this.addressResponses = addressResponses;
	}

	public List<BookingResponse> getBookingResponses() {
		return bookingResponses;
	}

	public void setBookingResponses(List<BookingResponse> bookingResponses) {
		this.bookingResponses = bookingResponses;
	}

	public UserDetailsResponse(String email, String first_name,
			String last_name, String gender,
			List<AddressResponse> addressResponses,
			List<BookingResponse> bookingResponses) {
		super();
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.addressResponses = addressResponses;
		this.bookingResponses = bookingResponses;
	}

}
