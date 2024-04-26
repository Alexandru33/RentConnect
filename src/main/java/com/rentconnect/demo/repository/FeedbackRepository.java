package com.rentconnect.demo.repository;

import com.rentconnect.demo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findAll();
    Optional<Feedback> findById(Integer id);

}
