package com.mediexpress.gestionventas.model;

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
@Table(name = "venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @Column(nullable = false)
    private Date fechaVenta;

    @Column(nullable = false)
    private int total;

    @Column(nullable = false)
    private Long idUsuario; 

}
