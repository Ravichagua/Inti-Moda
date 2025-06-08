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

@Controller
@RequestMapping("/users")
public class userController {

    @Autowired
    private UserRepository userRepository;
    //Registro de usuario
    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("documentTypeList", DocumentType.values());
        return "registro";
    }
    //tabla de usuarios
    @GetMapping("/table")
    public String testing(Model model ) {
        model.addAttribute("users",userRepository.findAll());
        return "table";
    }
    //Accion registrar formularios
    @PostMapping("/registUser")
    public String registUser(@ModelAttribute User user){
        userRepository.save(user);
        return "redirect:/users/table";
    }


}
