package com.pce.BookMeTutor.Model.Dto.Responses;

public class InvoiceResponse {

	private long id;

	private double amount;

	private String method;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public InvoiceResponse(long id, double amount, String method) {
		super();
		this.id = id;
		this.amount = amount;
		this.method = method;
	}

	public InvoiceResponse() {
		// TODO Auto-generated constructor stub
	}

}
