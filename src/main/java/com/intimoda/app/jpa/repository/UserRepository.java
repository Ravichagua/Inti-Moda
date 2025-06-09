package com.intimoda.app.jpa.repository;

import com.intimoda.app.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
}
