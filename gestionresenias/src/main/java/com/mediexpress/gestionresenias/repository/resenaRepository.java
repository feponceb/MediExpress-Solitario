package com.mediexpress.gestionresenias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediexpress.gestionresenias.model.resena;

@Repository
public interface resenaRepository extends JpaRepository<resena, Long>{
    
    List<resena> findByIdProducto(Long idProducto);
    List<resena> findByIdUsuario(Long idUsuario);

}
