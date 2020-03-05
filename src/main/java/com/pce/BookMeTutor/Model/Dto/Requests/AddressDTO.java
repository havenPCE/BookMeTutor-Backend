package com.pce.BookMeTutor.Model.Dto.Requests;

public class AddressDTO {
	
	private String line_1;
	private String line_2;
	private String city;
	private String pincode;
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
	public void setPincode(String pincodeString) {
		this.pincode = pincodeString;
	}
	@Override
	public String toString() {
		return "AddressDTO [line_1=" + line_1 + ", line_2=" + line_2 + ", city=" + city + ", pincodeString="
				+ pincode + "]";
	}
	public AddressDTO(String line_1, String line_2, String city, String pincodeString) {
		super();
		this.line_1 = line_1;
		this.line_2 = line_2;
		this.city = city;
		this.pincode = pincodeString;
	}
	public AddressDTO() {
		super();
	}
	
	

}
