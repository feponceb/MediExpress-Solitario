package com.mediexpress.gestionresenias.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reseña")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResena;

    @Column(nullable = false, length = 500)
    private String comentario;

    @Column(nullable = false)
    private int calificacion; 

    private Long idUsuario; 

    private Long idProducto; 

    @Column(nullable = false)
    private Date fecha;

}
