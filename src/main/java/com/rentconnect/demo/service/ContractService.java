package com.rentconnect.demo.service;

import com.rentconnect.demo.model.Contract;
import com.rentconnect.demo.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    ContractRepository contractRepository;

    public List<Contract> allContracts() {
        return contractRepository.findAll();

    }
}
