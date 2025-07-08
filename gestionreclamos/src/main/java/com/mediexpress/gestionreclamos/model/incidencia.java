package com.mediexpress.gestionreclamos.model;

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
@Table(name = "incidencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIncidencia;

    @Column(nullable = false, length = 500)
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "idMotivo", nullable = false)
    private motivo Motivo;

    @Column( nullable = false)
    private Long idVenta; 

    @Column(nullable = false)
    private Long idCliente;

}
