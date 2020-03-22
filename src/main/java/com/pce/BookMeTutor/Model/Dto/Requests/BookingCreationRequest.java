package com.pce.BookMeTutor.Model.Dto.Requests;

import java.util.Date;

public class BookingCreationRequest {

	private String subject;

	private String topic;

	private String classNumber;

	private String board;

	private String line1;

	private String line2;

	private String city;

	private String pincode;

	private Date schedule;

	private double amount;

	private String method;

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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
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

	public Date getSchedule() {
		return schedule;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	public BookingCreationRequest(String subject, String topic,
			String classNumber, String board, String line1, String line2,
			String city, String pincode, Date schedule, double amount,
			String method) {
		super();
		this.subject = subject;
		this.topic = topic;
		this.classNumber = classNumber;
		this.board = board;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.pincode = pincode;
		this.schedule = schedule;
		this.amount = amount;
		this.method = method;
	}

	public BookingCreationRequest() {
		// TODO Auto-generated constructor stub
	}

}
