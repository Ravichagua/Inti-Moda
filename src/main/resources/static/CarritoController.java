package com.intimoda.controller;

import com.intimoda.model.Carrito;
import com.intimoda.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public String verCarrito(Model model) {
        Carrito carrito = carritoService.getCarrito();
        model.addAttribute("carrito", carrito);
        model.addAttribute("total", carrito.calcularTotal());
        return "carrito";
    }

    @PostMapping("/agregar")
    public String agregarProducto(@RequestParam("productoId") Long productoId,
                                  @RequestParam(value = "cantidad", defaultValue = "1") int cantidad) {
        carritoService.agregarProducto(productoId, cantidad);
        return "redirect:/carrito";
    }

    @PostMapping("/remover")
    public String removerProducto(@RequestParam("productoId") Long productoId) {
        carritoService.removerProducto(productoId);
        return "redirect:/carrito";
    }
}
