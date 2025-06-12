package com.intimoda.app.services;

import com.intimoda.app.jpa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.intimoda.app.jpa.model.Producto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
    public void subirImagen(String nombre, MultipartFile imagen,Producto producto) throws IOException {
        if (!imagen.isEmpty()) {
            String nombreArchivo = imagen.getOriginalFilename();
            String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf("."));
            String nombreFinal = nombre + extension;
            producto.setImagenUrl(nombreFinal);
            Path ruta = Paths.get("uploads").resolve(nombreFinal);
            Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

        }else {
            producto.setImagenUrl(producto.getImagenUrl());
        }
    }

}