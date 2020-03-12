package com.pce.BookMeTutor.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pce.BookMeTutor.Model.Dao.Invoice;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

}
