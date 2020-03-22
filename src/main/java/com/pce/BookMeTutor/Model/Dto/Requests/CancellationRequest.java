package com.pce.BookMeTutor.Model.Dto.Requests;

public class CancellationRequest {

	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public CancellationRequest(String reason) {
		super();
		this.reason = reason;
	}

	public CancellationRequest() {
		// TODO Auto-generated constructor stub
	}

}
