package com.intimoda.app.jpa.controller;

import com.intimoda.app.jpa.model.Reclamacion;
import com.intimoda.app.jpa.repository.ReclamacionRepository;
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
@RequestMapping("/reclamos")
public class reclamacionController {

    @Autowired
    private ReclamacionRepository reclamoRepository;
    
    //tabla de usuarios
    @GetMapping("/tabla")
    public String tablaReclamo(Model model ) {
        model.addAttribute("reclamos",reclamoRepository.findAll());
        return "tablaReclamos";
    }
    //Accion registrar formularios
    @PostMapping("/guardarReclamo")
    public String guardarReclamo(@ModelAttribute Reclamacion reclamo){
        reclamoRepository.save(reclamo);
        return "redirect:/reclamos/tabla";
    }
    @PostMapping("/eliminar/{id}")
    public String eliminarReclamo(@PathVariable Long id){
        reclamoRepository.deleteById(id);
        return "redirect:/reclamos/tabla";
    }

    
}
