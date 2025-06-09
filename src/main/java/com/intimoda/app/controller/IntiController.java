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