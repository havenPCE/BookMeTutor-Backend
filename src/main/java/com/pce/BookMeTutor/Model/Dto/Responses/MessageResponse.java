package com.pce.BookMeTutor.Model.Dto.Responses;

public class MessageResponse {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageResponse(String message) {
		super();
		this.message = message;
	}

	public MessageResponse() {
	}

}
