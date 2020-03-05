package com.pce.BookMeTutor.Model.Dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;

@Entity
@Table(name="users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 7653478770835578197L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "first_name", nullable = false)
	private String fname;
	
	@Column(name = "last_name", nullable = true)
	private String lname;
	
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@ElementCollection
	@CollectionTable(name = "user_phones", joinColumns = @JoinColumn(referencedColumnName = "user_id"))
	private Set<String> phones;
	
	@Column(name = "verified", nullable = false, columnDefinition = "boolean default false")
	private boolean verified;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Address> addresses;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Booking> bookings;


	public User(long id, String password, @Email String email, String fname, String lname, String gender,
			Set<String> phones, boolean verified, Set<Address> addresses, Set<Booking> bookings) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.phones = phones;
		this.verified = verified;
		this.addresses = addresses;
		this.bookings = bookings;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public Set<String> getPhones() {
		return phones;
	}



	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}



	public boolean isVerified() {
		return verified;
	}



	public void setVerified(boolean verified) {
		this.verified = verified;
	}



	public Set<Address> getAddresses() {
		return addresses;
	}



	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}



	public Set<Booking> getBookings() {
		return bookings;
	}



	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public User() {
		super();
	}
	
	
	

}
