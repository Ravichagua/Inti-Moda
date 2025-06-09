package com.intimoda.app.services;

import com.intimoda.app.jpa.repository.UserRepository;
import java.util.Optional;
import com.intimoda.app.jpa.model.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class loginService {

    @Autowired
    private UserRepository userRepository;

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
