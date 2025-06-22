package com.mediexpress.gestioninventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestioninventario.model.estante;

@Repository
public interface estanteRepository extends JpaRepository<estante, Long>{

}
