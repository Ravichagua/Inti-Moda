package com.intimoda.app.controller;

import com.intimoda.app.jpa.model.User;
import com.intimoda.app.jpa.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Locale;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;
import com.intimoda.app.jpa.enums.DocumentType;
import com.intimoda.app.jpa.model.User;

@Controller
@RequiredArgsConstructor
public class IntiController {

    @GetMapping("/")
    public String root() {

        return "inicio";
    }
    @GetMapping("/libroReclamaciones")
    public String libroReclamaciones() {

        return "libroReclamaciones";
    }
    @GetMapping("/inicioSesion")
    public String inicioSesion() {

        return "inicioSesion";
    }

    @GetMapping("/inicio")
    public String inicio() {

        return "inicio";
    }
    @GetMapping("/carrito")
    public String carrito() {

        return "carrito";
    }
    //Registro de usuario
    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("documentTypeList", DocumentType.values());
        return "registro";
    }
    



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