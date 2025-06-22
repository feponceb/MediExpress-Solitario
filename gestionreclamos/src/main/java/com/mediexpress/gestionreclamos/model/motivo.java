package com.mediexpress.gestionreclamos.model;

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
@Table(name = "motivo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class motivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMotivo;

    @Column(nullable = false, unique = true)
    private String nombreMotiv;

}
