package com.pce.BookMeTutor.Model.Dto.Requests;

public class CompletionRequest {

	private String secret;

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public CompletionRequest(String secret) {
		super();
		this.secret = secret;
	}

	public CompletionRequest() {
		// TODO Auto-generated constructor stub
	}

}
