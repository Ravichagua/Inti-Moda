package com.intimoda.app.jpa.repository;

import com.intimoda.app.jpa.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long>{
    
}
