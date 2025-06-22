package com.mediexpress.gestionreclamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestionreclamos.model.ticket;

@Repository
public interface ticketRepository extends JpaRepository<ticket, Long>{

}
