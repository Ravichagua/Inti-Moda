package com.intimoda.app.jpa.repository;

import com.intimoda.app.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
}
