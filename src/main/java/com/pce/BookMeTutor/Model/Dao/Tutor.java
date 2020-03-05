package com.pce.BookMeTutor.Model.Dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "tutors")
public class Tutor implements Serializable {

	private static final long serialVersionUID = -7581281985922955056L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "tutor_id", unique = true, nullable = false)
	private long id;
	
	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "first_name", nullable = false)
	private String fname;
	
	@Column(name = "last_name")
	private String lname;
	
	@ElementCollection
	@CollectionTable(name = "tutor_phones", joinColumns = @JoinColumn(referencedColumnName = "tutor_id"))
	private Set<String> phone;
	
	@Column(name = "qualification", nullable = false)
	private String qualification;
	
	@Column(name = "verified", nullable = false, columnDefinition = "boolean default false")
	private boolean verified;
	
	@Column(name = "screening", nullable = false, columnDefinition = "varchar(20) default 'pending'")
	private String screening;
	
	@Column(name = "line_1")
	private String line1;
	
	@Column(name = "line_2")
	private String line2;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "pincode")
	private String pincode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Set<String> getPhone() {
		return phone;
	}

	public void setPhone(Set<String> phone) {
		this.phone = phone;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getScreening() {
		return screening;
	}

	public void setScreening(String screening) {
		this.screening = screening;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ", email=" + email + ", fname=" + fname + ", lname=" + lname + ", phone=" + phone
				+ ", qualification=" + qualification + ", verified=" + verified + ", screening=" + screening
				+ ", line1=" + line1 + ", line2=" + line2 + ", city=" + city + ", pincode=" + pincode + "]";
	}

	public Tutor(long id, @Email String email, String fname, String lname, Set<String> phone, String qualification,
			boolean verified, String screening, String line1, String line2, String city, String pincode) {
		super();
		this.id = id;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.qualification = qualification;
		this.verified = verified;
		this.screening = screening;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.pincode = pincode;
	}

	public Tutor() {
		super();
	}
	
	
	
}
