package com.rentconnect.demo.repository;

import com.rentconnect.demo.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    List<Contract> findAll();
    Optional<Contract> findById(Integer id);

}
