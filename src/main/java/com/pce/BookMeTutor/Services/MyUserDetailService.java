package com.pce.BookMeTutor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pce.BookMeTutor.Model.StudentDetails;
import com.pce.BookMeTutor.Model.TutorDetails;
import com.pce.BookMeTutor.Model.Dao.Tutor;
import com.pce.BookMeTutor.Model.Dao.UserEntity;
import com.pce.BookMeTutor.Repo.TutorRepo;
import com.pce.BookMeTutor.Repo.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	TutorRepo tutorRepo;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		UserEntity userEntity = userRepo.findByEmail(username);
		if (userEntity == null) {
			Tutor tutor = tutorRepo.findByEmail(username);
			if (tutor == null) {
				throw new UsernameNotFoundException("User not found!");
			}
			return new TutorDetails(tutor);
		}

		return new StudentDetails(userEntity);
	}

}
