package com.rentconnect.demo.repository;

import com.rentconnect.demo.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    List<Property> findAll();
    Optional<Property> findById(Integer id);

    @Query(nativeQuery = true,
            value ="SELECT *" +
                    "FROM rentconnect_schema.property\n" +
                    "WHERE rentconnect_schema.property.user_id = :id")
    List<Property> personalProperties(Integer id);
}
