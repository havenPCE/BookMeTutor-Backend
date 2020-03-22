package com.pce.BookMeTutor.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pce.BookMeTutor.Model.Dao.Tutor;

@Repository
public interface TutorRepo extends JpaRepository<Tutor, Long> {

	Tutor findByEmail(String email);

	Tutor findFirstByOrderByLastSelectedAsc();

}
