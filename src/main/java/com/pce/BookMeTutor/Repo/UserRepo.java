package com.pce.BookMeTutor.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pce.BookMeTutor.Model.Dao.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	
	User findByEmail(String email);

}
