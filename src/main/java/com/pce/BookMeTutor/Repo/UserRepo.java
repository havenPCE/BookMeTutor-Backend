package com.pce.BookMeTutor.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pce.BookMeTutor.Model.Dao.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);

}
