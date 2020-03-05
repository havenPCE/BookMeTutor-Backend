package com.pce.BookMeTutor.Model.Dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "topics")
public class Topic implements Serializable {

	private static final long serialVersionUID = 5985277015730100432L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topic_id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "topic_name")
	private String name;
	
	@ManyToOne
	private Subject subject;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", name=" + name + ", subject=" + subject + "]";
	}

	public Topic(long id, String name, Subject subject) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
	}

	public Topic() {
		super();
	}
	
	
}
