package com.pce.BookMeTutor.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pce.BookMeTutor.Model.Dao.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

	@Query("SELECT b from Booking b WHERE b.user.id = :id")
	List<Booking> findAllByUserId(@Param("id") Long id);

	@Query("SELECT b from Booking b WHERE b.handler.id = :id")
	List<Booking> findAllByTutorId(@Param("id") Long id);

}
