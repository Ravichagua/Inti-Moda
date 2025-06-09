package com.intimoda.app.jpa.model;

import com.intimoda.app.jpa.enums.DocumentType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data 
@NoArgsConstructor
@AllArgsConstructor 
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private float precio;
    private int stock;
    private String categoria;
}