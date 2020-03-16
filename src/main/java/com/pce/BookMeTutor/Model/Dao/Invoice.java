package com.pce.BookMeTutor.Model.Dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

	private static final long serialVersionUID = -5310387311015466077L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id", unique = true, nullable = false)
	private long id;

	@OneToOne
	private Booking booking;

	@Column(name = "amount", columnDefinition = "Decimal(10,2) default '100.00'")
	private double amount;

	@Column(name = "payment_method")
	private String method;

	@Column(name = "payment_source")
	private String source;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", booking=" + booking + ", amount="
				+ amount + ", method=" + method + ", source=" + source + "]";
	}

	public Invoice(long id, Booking booking, double amount, String method,
			String source) {
		super();
		this.id = id;
		this.booking = booking;
		this.amount = amount;
		this.method = method;
		this.source = source;
	}

	public Invoice() {
		super();
	}

}
