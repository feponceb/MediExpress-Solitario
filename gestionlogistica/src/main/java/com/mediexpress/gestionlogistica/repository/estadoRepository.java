package com.mediexpress.gestionlogistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestionlogistica.model.estado;

@Repository
public interface estadoRepository extends JpaRepository<estado, Long>{

}
