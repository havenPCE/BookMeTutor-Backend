package com.pce.BookMeTutor.Model.Dto;

public class EmailResponse {
	
	private String to;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public EmailResponse(String to) {
		super();
		this.to = to;
	}

	public EmailResponse() {
		super();
	}
	
	

}
