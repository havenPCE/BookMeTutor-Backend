package com.pce.BookMeTutor.Model.Dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import javax.persistence.GenerationType;

@Entity
@Table(name = "subjects")
public class Subject implements Serializable {

	private static final long serialVersionUID = -5671707682069283051L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "subject_name", nullable = false)
	private String name;
	
	@Column(name = "class_number", nullable = false)
	private int classNumber;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Topic> topics;

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

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", topics=" + topics + "]";
	}

	public Subject(long id, String name, Set<Topic> topics) {
		super();
		this.id = id;
		this.name = name;
		this.topics = topics;
	}

	public Subject() {
		super();
	}
	
	
	
}
