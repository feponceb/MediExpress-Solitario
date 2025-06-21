package com.mediexpress.gestionprods.model;

import java.util.Date;

import jakarta.persistence.Entity;

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

    private Long idProd;
    private String nombre;
    private String descripcion;
    private int stock;
    private int precio;
    private int unidad;
    private Date fecha_exp;

    /* 
    @ManyToOne
    @JoinColumn(name = "idEstado", nullable = true)
    private Estado estado;
    */
    
}
