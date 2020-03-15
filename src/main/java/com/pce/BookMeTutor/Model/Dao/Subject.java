package com.pce.BookMeTutor.Model.Dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@ElementCollection
	@CollectionTable(name = "subject_topics", joinColumns = @JoinColumn(referencedColumnName = "subject_id"))
	private Set<String> topics;
	

	
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



	public int getClassNumber() {
		return classNumber;
	}



	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}



	public Set<String> getTopics() {
		return topics;
	}



	public void setTopics(Set<String> topics) {
		this.topics = topics;
	}



	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", classNumber="
				+ classNumber + ", topics=" + topics + "]";
	}



	public Subject(long id, String name, int classNumber, Set<String> topics) {
		super();
		this.id = id;
		this.name = name;
		this.classNumber = classNumber;
		this.topics = topics;
	}



	public Subject() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
