package com.rentconnect.demo.repository;

import com.rentconnect.demo.model.Viewing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ViewingRepository extends JpaRepository<Viewing, Integer> {
    List<Viewing> findAll();
    Optional<Viewing> findById(Integer id);

    @Query(nativeQuery = true,
            value ="SELECT * " +
                    "FROM rentconnect_schema.viewing " +
                    "WHERE rentconnect_schema.viewing.user_id = :id ")
    List<Viewing> personalViewings(Integer id);
}
