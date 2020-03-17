package com.pce.BookMeTutor.Model.Dto.Requests;

import java.util.Date;

public class UpdateBookingRequest {
	
	private String comment;
	private Integer score;
	private Date new_date;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Date getNew_date() {
		return new_date;
	}
	public void setNew_date(Date new_date) {
		this.new_date = new_date;
	}
	public UpdateBookingRequest(String comment, Integer score, Date new_date) {
		super();
		this.comment = comment;
		this.score = score;
		this.new_date = new_date;
	}
	public UpdateBookingRequest() {
		// TODO Auto-generated constructor stub
	}
	
	
}
