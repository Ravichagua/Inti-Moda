package com.intimoda.app.jpa.controller;

import com.intimoda.app.jpa.model.Producto;
import com.intimoda.app.jpa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.intimoda.app.services.ProductoService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //#####################     CRUD      ###########################
    //LEER / READ
    @GetMapping
    public String mostrarProductos(Model model ) {
        model.addAttribute("productos",productoService.obtenerTodos());
        return "tablaProductos";
    }
    //CREAR / CREATE
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto,
                                  @RequestParam("nombre") String nombre,
                                  @RequestParam("imagen") MultipartFile imagen) throws IOException {
        productoService.subirImagen(nombre,imagen,producto);
        productoService.guardar(producto);
        return "redirect:/productos";
    }
    //ELIMINAR / DELETE
    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id){
        productoService.eliminar(id);
        return "redirect:/productos";
    }
    //EDITAR / UPDATE
    @PostMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id,
                                 @ModelAttribute Producto productoEditado,
                                 @RequestParam("nombre") String nombre,
                                 @RequestParam("imagen") MultipartFile imagen) throws IOException {
        String imagenOriginal= productoService.obtenerPorId(id).getImagenUrl();
        productoEditado.setId(id);
        productoService.subirImagen(nombre,imagen,productoEditado);

        if(productoEditado.getImagenUrl()==null){
            productoEditado.setImagenUrl(imagenOriginal);
        }
        productoService.guardar(productoEditado);
        return "redirect:/productos";
    }
    //###############################################################

}