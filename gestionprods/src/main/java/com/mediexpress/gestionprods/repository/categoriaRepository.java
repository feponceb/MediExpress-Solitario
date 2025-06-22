package com.mediexpress.gestionprods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestionprods.model.categoria;

@Repository
public interface categoriaRepository extends JpaRepository<categoria, Long>{

}
