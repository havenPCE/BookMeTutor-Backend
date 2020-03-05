package com.pce.BookMeTutor.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pce.BookMeTutor.Model.Dao.Invoice;

@Repository
public interface InvoiceRepo extends CrudRepository<Invoice, Long> {

}
