package com.example.app.controller;

import com.example.app.model.Producto;
import com.example.app.repository.ProductoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Locale;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
@Controller
public class IntiController {

    @GetMapping("/")
    public String root() {
        return "inicio";
    }
    @GetMapping("/libroReclamaciones")
    public String libroReclamaciones() {

        return "libroReclamaciones"; // mostrará about.html
    }
    @GetMapping("/inicioSesion")
    public String inicioSesion() {

        return "inicioSesion"; // mostrará about.html
    }
    @GetMapping("/registro")
    public String registro() {

        return "registro"; // mostrará about.html
    }
    @GetMapping("/inicio")
    public String inicio() {

        return "inicio"; // mostrará about.html
    }
    @GetMapping("/carrito")
    public String carrito() {

        return "carrito"; // mostrará about.html
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        ProductoDAO dao = new ProductoDAO();
        List<Producto> productos = dao.obtenerProductos();
        model.addAttribute("listaProductos", productos);

        return "about"; // mostrará about.html
    }

    
    @GetMapping("/lang")
    public String cambiarIdioma(@RequestParam String lang, HttpServletRequest request, HttpServletResponse response) {
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
    if (localeResolver != null) {
        localeResolver.setLocale(request, response, new Locale(lang));
    }
    String referer = request.getHeader("Referer");
    if (referer != null) {
        return "redirect:" + referer;
    }
    return "redirect:/";
    }
}