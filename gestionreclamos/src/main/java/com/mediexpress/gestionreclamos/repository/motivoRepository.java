package com.mediexpress.gestionreclamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestionreclamos.model.motivo;

@Repository
public interface motivoRepository extends JpaRepository<motivo, Long>{

}
