package com.pce.BookMeTutor.Model.Dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.CascadeType;

import javax.persistence.GenerationType;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {
	

	private static final long serialVersionUID = 8958089464653520081L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id", unique = true, nullable = false)
	private long id;
	
	@ManyToOne
	private UserEntity user;
	
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Tutor handler;
	
	@Column(nullable = false)
	private String subject;
	
	@Column(nullable = false)
	private String topic;
	
	@Column(name = "class_number", nullable = false)
	private String classNumber;
	
	@Column(name = "board", nullable = false)
	private String board;
	
	@Column(name = "line_1", nullable = false)
	private String line1;
	
	@Column(name = "line_2", nullable = false)
	private String line2;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "pincode", nullable = false)
	private String pincode;
	
	@Column(name = "scheduled_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date schedule;
	
	@Column(name = "deadline_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date deadline;
	
	@Column(name = "rescheduled", nullable = false, columnDefinition = "boolean default false")
	private boolean rescheduled;
	
	@Column(name = "secret", nullable = false, length = 4)
	private String secret;
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "user_comment")
	private String comment;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "booking_status", nullable = false, columnDefinition = "varchar(20) default 'pending'")
	private String status;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Invoice invoice;
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Tutor getHandler() {
		return handler;
	}

	public void setHandler(Tutor handler) {
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

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", user=" + user + ", handler=" + handler
				+ ", subject=" + subject + ", topic=" + topic + ", classNumber="
				+ classNumber + ", board=" + board + ", line1=" + line1
				+ ", line2=" + line2 + ", city=" + city + ", pincode=" + pincode
				+ ", schedule=" + schedule + ", deadline=" + deadline
				+ ", rescheduled=" + rescheduled + ", secret=" + secret
				+ ", score=" + score + ", comment=" + comment + ", reason="
				+ reason + ", status=" + status + ", invoice=" + invoice + "]";
	}

	public Booking(long id, UserEntity user, Tutor handler, String subject,
			String topic, String classNumber, String board, String line1,
			String line2, String city, String pincode, Date schedule,
			Date deadline, boolean rescheduled, String secret, int score,
			String comment, String reason, String status, Invoice invoice) {
		super();
		this.id = id;
		this.user = user;
		this.handler = handler;
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
		this.invoice = invoice;
	}

	public Booking() {
		super();
	}

	
	
}
