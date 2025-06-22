package com.mediexpress.gestionventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestionventas.model.venta;

@Repository
public interface ventaRepository extends JpaRepository<venta, Long>{

}
