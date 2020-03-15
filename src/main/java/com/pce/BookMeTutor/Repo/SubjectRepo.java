package com.pce.BookMeTutor.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pce.BookMeTutor.Model.Dao.Subject;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {
	
	public Subject findBySubjectNameAndClassNumber(String subjectName, int classNumber);
	

}
