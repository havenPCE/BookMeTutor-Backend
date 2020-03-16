package com.pce.BookMeTutor.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pce.BookMeTutor.Model.Dao.Subject;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {

	@Query("SELECT s from Subject s WHERE s.name = :subject AND s.classNumber = :classNumber")
	public Subject findBySubjectNameAndClassNumber(
			@Param("subject") String subject,
			@Param("classNumber") int classNumber);

}
