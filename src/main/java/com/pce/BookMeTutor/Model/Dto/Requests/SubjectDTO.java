package com.pce.BookMeTutor.Model.Dto.Requests;

import java.util.List;

public class SubjectDTO {
	
	private String subjectName;
	private int classNumber;
	private List<String> topic;
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
	public List<String> getTopic() {
		return topic;
	}
	public void setTopic(List<String> topic) {
		this.topic = topic;
	}
	@Override
	public String toString() {
		return "SubjectDTO [subjectName=" + subjectName + ", classNumber="
				+ classNumber + ", topic=" + topic + "]";
	}
	public SubjectDTO(String subjectName, int classNumber, List<String> topic) {
		super();
		this.subjectName = subjectName;
		this.classNumber = classNumber;
		this.topic = topic;
	}
	public SubjectDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

}
