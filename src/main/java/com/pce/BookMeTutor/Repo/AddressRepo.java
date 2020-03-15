package com.pce.BookMeTutor.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pce.BookMeTutor.Model.Dao.AddressEntity;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity, Long> {

}
