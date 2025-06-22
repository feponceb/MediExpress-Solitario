package com.mediexpress.gestioninventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestioninventario.model.fila;

@Repository
public interface filaRepository extends JpaRepository<fila, Long>{

}
