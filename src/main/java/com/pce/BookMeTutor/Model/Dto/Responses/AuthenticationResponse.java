package com.pce.BookMeTutor.Model.Dto.Responses;

public class AuthenticationResponse {

	private String jwtToken;

	public AuthenticationResponse(String jwtToken) {
		super();
		this.setJwtToken(jwtToken);
	}

	public AuthenticationResponse() {
		super();
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
}
