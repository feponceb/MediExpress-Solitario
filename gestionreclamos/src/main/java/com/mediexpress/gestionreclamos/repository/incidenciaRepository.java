package com.mediexpress.gestionreclamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestionreclamos.model.incidencia;

@Repository
public interface incidenciaRepository extends JpaRepository<incidencia, Long>{

}
