package com.rentconnect.demo.repository;

import com.rentconnect.demo.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findAll();
    Optional<Inventory> findById(Integer id);

}
