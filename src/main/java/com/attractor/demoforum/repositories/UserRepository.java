package com.attractor.demoforum.repositories;

import com.attractor.demoforum.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String username);
    User getUserById(Long Id);

    List<User> findAllBy();


}
