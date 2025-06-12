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
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/reclamos")
public class reclamacionController {

    @Autowired
    private ReclamacionRepository reclamoRepository;

    //#####################     CRUD      ###########################
    //LEER / READ
    @GetMapping
    public String mostrarReclamos(Model model ) {
        model.addAttribute("reclamos",reclamoRepository.findAll());
        return "tablaReclamos";
    }
    //CREAR / CREATE
    @PostMapping("/guardar")
    public String guardarReclamo(@ModelAttribute Reclamacion reclamo){
        reclamoRepository.save(reclamo);
        return "redirect:/reclamos";
    }
    //ELIMINAR / DELETE
    @PostMapping("/eliminar/{id}")
    public String eliminarReclamo(@PathVariable Long id){
        reclamoRepository.deleteById(id);
        return "redirect:/reclamos";
    }
    //EDITAR / UPDATE
    @GetMapping("/editar/{id}")
    public String editarReclamo(@PathVariable Long id, Model model) {
        Reclamacion reclamacion = reclamoRepository.findById(id).orElseThrow();
        model.addAttribute("reclamo", reclamacion);
        return "redirect:/reclamos";
    }
    //###############################################################
    
}
