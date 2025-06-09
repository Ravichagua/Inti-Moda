package com.intimoda.app.jpa.controller;

import com.intimoda.app.jpa.enums.DocumentType;
import com.intimoda.app.jpa.model.User;
import com.intimoda.app.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intimoda.app.services.loginService;


@Controller
@RequestMapping("/users")
public class userController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private loginService loginService;


    
    //tabla de usuarios
    @GetMapping("/table")
    public String testing(Model model ) {
        model.addAttribute("users",userRepository.findAll());
        return "tablaUsuarios";
    }
    //Accion registrar formularios
    @PostMapping("/registUser")
    public String registUser(@ModelAttribute User user){
        userRepository.save(user);
        return "redirect:/users/table";
    }
    
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
