package com.mediexpress.gestionlogistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestionlogistica.model.encargo;

@Repository
public interface encargoRepository extends JpaRepository<encargo, Long>{

}
