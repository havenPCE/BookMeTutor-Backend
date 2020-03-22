package com.pce.BookMeTutor.Model.Dto.Responses;

public class EmailResponse {

	private String message;

	public EmailResponse(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "EmailResponse [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EmailResponse() {
		super();
	}

}
