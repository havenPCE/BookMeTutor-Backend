package com.pce.BookMeTutor.Model.Dto.Responses;

public class AddressResponse {

	private long id;
	private String line_1;
	private String line_2;
	private String city;
	private String pincode;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLine_1() {
		return line_1;
	}
	public void setLine_1(String line_1) {
		this.line_1 = line_1;
	}
	public String getLine_2() {
		return line_2;
	}
	public void setLine_2(String line_2) {
		this.line_2 = line_2;
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
	public AddressResponse(long id, String line_1, String line_2, String city,
			String pincode) {
		super();
		this.id = id;
		this.line_1 = line_1;
		this.line_2 = line_2;
		this.city = city;
		this.pincode = pincode;
	}
	public AddressResponse() {
		// TODO Auto-generated constructor stub
	}

}
