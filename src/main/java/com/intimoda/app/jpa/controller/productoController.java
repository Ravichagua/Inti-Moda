package com.intimoda.app.jpa.controller;

import com.intimoda.app.jpa.model.Producto;
import com.intimoda.app.jpa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/productos")
public class productoController {

    @Autowired
    private ProductoRepository productoRepository;

    //#####################     CRUD      ###########################
    //LEER / READ
    @GetMapping
    public String mostrarProductos(Model model ) {
        model.addAttribute("productos",productoRepository.findAll());
        return "tablaProductos";
    }
    //CREAR / CREATE
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto){
        productoRepository.save(producto);
        return "redirect:/productos";
    }
    //ELIMINAR / DELETE
    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id){
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }
    //EDITAR / UPDATE
    @GetMapping("/productos/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        model.addAttribute("producto", producto);
        return "redirect:/productos";
    }
    //###############################################################

}