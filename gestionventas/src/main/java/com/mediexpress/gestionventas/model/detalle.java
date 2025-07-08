package com.mediexpress.gestionventas.model;

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
@Table(name = "detalle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private int precioUnit;

    @Column(nullable = false)
    private int subtotal;

    @ManyToOne
    @JoinColumn(name = "idVenta", nullable = false)
    private venta venta;

    private Long idProducto; 

}
