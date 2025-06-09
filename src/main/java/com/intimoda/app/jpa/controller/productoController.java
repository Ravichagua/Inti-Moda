package com.intimoda.app.jpa.controller;

import com.intimoda.app.jpa.enums.DocumentType;
import com.intimoda.app.jpa.model.Producto;
import com.intimoda.app.jpa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.intimoda.app.services.loginService;


@Controller
@RequestMapping("/producto")
public class productoController {

    @Autowired
    private ProductoRepository productoRepository;


    //tabla de usuarios
    @GetMapping("/tabla")
    public String MostrarProductos(Model model ) {
        model.addAttribute("productos",productoRepository.findAll());
        return "tablaProductos";
    }
    @GetMapping("/gestion")
    public String gestion(@ModelAttribute Producto producto){

        return "gestionProductos";
    }

    //Accion registrar formularios
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto){
        productoRepository.save(producto);
        return "redirect:/producto/tabla";
    }
    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id){
        productoRepository.deleteById(id);
        return "redirect:/producto/tabla";
    }


}