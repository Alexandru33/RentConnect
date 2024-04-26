package com.rentconnect.demo.repository;

import com.rentconnect.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByName(String name);




}
