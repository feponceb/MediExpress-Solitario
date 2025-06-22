package com.mediexpress.gestionventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestionventas.model.detalle;

@Repository
public interface detalleRepository extends JpaRepository<detalle, Long>{
    List<detalle> findByVenta_IdVenta(Long idVenta);

}
