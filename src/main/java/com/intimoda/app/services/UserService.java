package com.intimoda.app.services;

import com.intimoda.app.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.intimoda.app.jpa.repository.UserRepository;
import com.intimoda.app.jpa.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    /*Password encoder arriba*/

    public List<User> obtenerTodos() {
        return userRepository.findAll();
    }

    public User obtenerPorId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /*public void guardar(User user) {
        userRepository.save(user);
    }*/
    public void guardar(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setLastname(dto.getLastname());
        user.setDocumentType(dto.getDocumentType());
        user.setDocumentNumber(dto.getDocumentNumber());
        user.setPhone(dto.getPhone());

        user.setRole("USER");

        userRepository.save(user);
    }

    public void guardarEdit(User user) {
        userRepository.save(user);
    }

    public void eliminar(Long id) {
        userRepository.deleteById(id);
    }

    public boolean login(String email, String password) {
        Optional<User> userBackend = userRepository.findByEmail(email);

        if (userBackend.isPresent()) {
            User usuario = userBackend.get();

            if(usuario.getPassword().equals(password)){
                System.out.println("Success");
                return true;
            }else{
                System.out.println("Failure");
                return false;
            }
        }
        System.out.println("Failure");
        return false;
    }

}
