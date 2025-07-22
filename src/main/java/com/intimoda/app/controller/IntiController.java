package com.intimoda.app.controller;

import com.intimoda.app.DTO.LoginRequest;
import com.intimoda.app.DTO.UserDTO;
import com.intimoda.app.jpa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;

import com.intimoda.app.jpa.model.DocumentType;
import com.intimoda.app.jpa.repository.ProductoRepository;

@Controller
@RequiredArgsConstructor
public class IntiController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("productos",productoRepository.findAll());
        return "inicio";
    }
    @GetMapping("/inicio")
    public String inicio(Model model) {
        model.addAttribute("productos",productoRepository.findAll());
        return "inicio";
    }
    @GetMapping("/libroReclamaciones")
    public String libroReclamaciones() {

        return "libroReclamaciones";
    }
    @GetMapping("/inicioSesion")
    public String inicioSesion(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "inicioSesion";
    }

    @GetMapping("/carrito")
    public String carrito() {

        return "carrito";
    }
    //Registro de usuario
    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("documentTypeList", DocumentType.values());
        return "registro";
    }
    //Este id es para el lenguage 'en' o 'es'
    @GetMapping("/{id}")
    public String switchLang(@PathVariable String id, HttpServletRequest request) {
        // Validar idioma
        if (!id.equals("es") && !id.equals("en")) {
            return "redirect:/"; // idioma no válido, redirige al inicio
        }

        // Obtener la URL anterior
        String referer = request.getHeader("Referer");
        if (referer == null) {
            referer = "/";
        }
        String url;
if (referer.contains("?")) {
    // Si ya tiene parámetros, verificar si tiene lang
    if (referer.contains("lang=")) {
        // Reemplazar el valor existente de lang
        url = referer.replaceAll("lang=[^&]*", "lang=" + id);
    } else {
        // Añadir el nuevo parámetro lang
        url = referer + "&lang=" + id;
    }
} else {
    // Si no tiene parámetros, añadir el lang
    url = referer + "?lang=" + id;
}

        return "redirect:" + url;
    }
}