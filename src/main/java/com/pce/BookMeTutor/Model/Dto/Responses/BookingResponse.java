package com.pce.BookMeTutor.Model.Dto.Responses;

import java.util.Date;

public class BookingResponse {
	private long id;

	private String handler;

	private String student;

	private String subject;

	private String topic;

	private String classNumber;

	private String board;

	private String line1;

	private String line2;

	private String city;

	private String pincode;

	private Date schedule;

	private Date deadline;

	private boolean rescheduled;

	private String secret;

	private int score;

	private String comment;

	private String reason;

	private String status;

	private InvoiceResponse invoiceResponse;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
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

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public boolean isRescheduled() {
		return rescheduled;
	}

	public void setRescheduled(boolean rescheduled) {
		this.rescheduled = rescheduled;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public InvoiceResponse getInvoiceResponse() {
		return invoiceResponse;
	}

	public void setInvoiceResponse(InvoiceResponse invoiceResponse) {
		this.invoiceResponse = invoiceResponse;
	}

	public BookingResponse(long id, String handler, String student,
			String subject, String topic, String classNumber, String board,
			String line1, String line2, String city, String pincode,
			Date schedule, Date deadline, boolean rescheduled, String secret,
			int score, String comment, String reason, String status,
			InvoiceResponse invoiceResponse) {
		super();
		this.id = id;
		this.handler = handler;
		this.student = student;
		this.subject = subject;
		this.topic = topic;
		this.classNumber = classNumber;
		this.board = board;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.pincode = pincode;
		this.schedule = schedule;
		this.deadline = deadline;
		this.rescheduled = rescheduled;
		this.secret = secret;
		this.score = score;
		this.comment = comment;
		this.reason = reason;
		this.status = status;
		this.invoiceResponse = invoiceResponse;
	}

	public BookingResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

}
