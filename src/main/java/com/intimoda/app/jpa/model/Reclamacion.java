package com.intimoda.app.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reclamaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reclamacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Datos del consumidor
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private String telefono;
    private String email;
    private String direccion;

    // Detalles del reclamo
    private String tipoReclamo; // "reclamo" o "queja"
    private String numeroPedido;
    private LocalDate fechaCompra;
    private String productoServicio;
    private String descripcion;

    // Pedido del consumidor
    private String pedidoConsumidor;
}
