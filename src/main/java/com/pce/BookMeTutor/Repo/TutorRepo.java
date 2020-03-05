package com.pce.BookMeTutor.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pce.BookMeTutor.Model.Dao.Tutor;

@Repository
public interface TutorRepo extends CrudRepository<Tutor, Long> {
	
	Tutor findByEmail(String email);
	
}
