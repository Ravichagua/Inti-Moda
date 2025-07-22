package com.intimoda.app.controller;

import com.intimoda.app.DTO.LoginRequest;
import com.intimoda.app.jpa.model.User;
import com.intimoda.app.jpa.repository.UserRepository;
import com.intimoda.app.security.JwtResponse;
import com.intimoda.app.security.JwtUtils;
import com.intimoda.app.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;


    @GetMapping("/inicioSesion")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "inicioSesion"; // thymeleaf mostrará el formulario
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("loginRequest") LoginRequest loginRequest,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "inicioSesion"; // vuelve al formulario con errores
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // Obtenemos los detalles del usuario autenticado
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String email = userDetails.getUsername();
            String role = userDetails.getUser().getRole();

            // Mostrar los GrantedAuthority en consola
            System.out.println("\n--- Authorities del usuario ---");
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                System.out.println("Authority: " + authority.getAuthority());
            }

            // Generamos el token
            String token = jwtUtils.generateToken(email, role);

            System.out.println("\n--- TOKEN generado ---");
            System.out.println("TOKEN: " + token);

            redirectAttributes.addFlashAttribute("loginSuccess", "Inicio de sesión exitoso");
            model.addAttribute("jwtToken", token);

            return "redirect:/";

        } catch (AuthenticationException e) {
            model.addAttribute("loginError", "Email o contraseña inválidos");
            return "inicioSesion";
        }
    }



   /* @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER"); // Forzamos rol normal
        userRepository.save(user);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }*/
}
