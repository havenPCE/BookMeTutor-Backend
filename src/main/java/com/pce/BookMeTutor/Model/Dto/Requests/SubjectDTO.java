package com.pce.BookMeTutor.Model.Dto.Requests;

import java.util.Set;

public class SubjectDTO {

	private String subjectName;
	private int classNumber;
	private Set<String> topic;
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}
	public Set<String> getTopic() {
		return topic;
	}
	public void setTopic(Set<String> topic) {
		this.topic = topic;
	}
	@Override
	public String toString() {
		return "SubjectDTO [subjectName=" + subjectName + ", classNumber="
				+ classNumber + ", topic=" + topic + "]";
	}
	public SubjectDTO(String subjectName, int classNumber, Set<String> topic) {
		super();
		this.subjectName = subjectName;
		this.classNumber = classNumber;
		this.topic = topic;
	}
	public SubjectDTO() {
		// TODO Auto-generated constructor stub
	}

}
