package com.intimoda.app.jpa.controller;
import com.intimoda.app.jpa.model.Producto;
import com.intimoda.app.jpa.model.User;
import com.intimoda.app.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.intimoda.app.services.loginService;


@Controller
@RequestMapping("/users")
public class userController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private loginService loginService;

    //#####################     CRUD      ###########################
    //LEER / READ
    @GetMapping
    public String mostrarUsers(Model model ) {
        model.addAttribute("users",userRepository.findAll());
        return "tablaUsuarios";
    }
    //CREAR / CREATE
    @PostMapping("/guardar")
    public String guardarUser(@ModelAttribute User user){
        userRepository.save(user);
        return "redirect:/users";
    }
    //ELIMINAR / DELETE
    @PostMapping("/eliminar/{id}")
    public String eliminarUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return "redirect:/users";
    }
    //EDITAR / UPDATE
    @GetMapping("/productos/editar/{id}")
    public String editarUser(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("users", user);
        return "redirect:/users";
    }
    //###############################################################




    @PostMapping("/login")
    public String authentification(@RequestParam String email,@RequestParam String password,Model modeloLogin) {
        if(loginService.login(email,password)){
           modeloLogin.addAttribute("successLogin",true);
            return "redirect:/inicio";
            
        }else{
            modeloLogin.addAttribute("errorLogin",true);
            return "inicioSesion";
        }
        
    }
}
