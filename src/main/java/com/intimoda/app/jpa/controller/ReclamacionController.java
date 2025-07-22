package com.intimoda.app.jpa.controller;

import com.intimoda.app.jpa.model.Producto;
import com.intimoda.app.jpa.model.Reclamacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.intimoda.app.services.ReclamacionService;

@Controller
@RequestMapping("/reclamos")
public class ReclamacionController {

    @Autowired
    private ReclamacionService reclamacionService;


    //#####################     CRUD      ###########################
    //LEER / READ
    @GetMapping
    public String mostrarReclamos(Model model ) {
        model.addAttribute("reclamos",reclamacionService.obtenerTodos());
        return "tablaReclamos";
    }
    //CREAR / CREATE
    @PostMapping("/guardar")
    public String guardarReclamo(@ModelAttribute Reclamacion reclamo){
        reclamacionService.guardar(reclamo);
        return "redirect:/reclamos";
    }
    //ELIMINAR / DELETE
    @PostMapping("/eliminar/{id}")
    public String eliminarReclamo(@PathVariable Long id){
        reclamacionService.eliminar(id);
        return "redirect:/reclamos";
    }
    //EDITAR / UPDATE
    @PostMapping("/editar/{id}")
    public String editarReclamo(@PathVariable Long id, @ModelAttribute Reclamacion reclamoEditado) {
        reclamoEditado.setId(id);
        reclamacionService.guardar(reclamoEditado);
        return "redirect:/reclamos";
    }
    //###############################################################
    
}
