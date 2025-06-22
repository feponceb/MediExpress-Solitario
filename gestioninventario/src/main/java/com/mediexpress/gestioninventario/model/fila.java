package com.mediexpress.gestioninventario.model;

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
@Table(name = "fila")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class fila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFila;

    @Column(nullable = false)
    private String nombreFila;

    @ManyToOne
    @JoinColumn(name = "idEstante", nullable = false)
    private estante Estante;

}
