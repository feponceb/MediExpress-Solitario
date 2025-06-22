package com.mediexpress.gestionprods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestionprods.model.producto;

@Repository
public interface productoRepository extends JpaRepository<producto, Long> {

}
