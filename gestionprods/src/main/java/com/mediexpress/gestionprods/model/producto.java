package com.mediexpress.gestionprods.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProd;

    @Column(nullable = false, length = 50)
    private String nombre;
   
    @Column(nullable = true, length = 50)
    private String descripcion;
   
    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private int precio;

    @Column(nullable = false)
    private int unidad;

    @Column(nullable = true)
    private Date fecha_exp;

    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = true)
    private categoria Categoria;  
}
