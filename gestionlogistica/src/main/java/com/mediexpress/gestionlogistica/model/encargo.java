package com.mediexpress.gestionlogistica.model;


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
@Table(name = "encargo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class encargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEncargo;

    @Column(nullable = false)
    private Long idOrden;  // referencia a la orden en microservicio ventas

    @Column(nullable = false)
    private String estado;  

    @Column(nullable = false)
    private Date fechaSalida;

    @Column(nullable = true)
    private Date fechaEntregaEstimada;

    @Column(nullable = true)
    private Date fechaFinalizada;

    @Column(nullable = true)
    private String tracking;  // Código de seguimiento

    @ManyToOne
    @JoinColumn(name = "idEstado", nullable = false)
    private estado estadoR;

}
