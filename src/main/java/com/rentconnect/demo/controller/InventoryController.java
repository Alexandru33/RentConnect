package com.rentconnect.demo.controller;

import com.rentconnect.demo.model.Inventory;
import com.rentconnect.demo.dto.InventoryDTO;
import com.rentconnect.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/getAllInventories")
    public List<Inventory> getAllInventories()
    {
        return inventoryService.allInventories();
    }

    @PostMapping("/addInventory")
    public ResponseEntity addInventory(
            @RequestBody InventoryDTO inventoryDTO)
    {
        System.out.println(inventoryDTO.getChair() + inventoryDTO.getBed());
        InventoryDTO returnedDTO = inventoryService.addInventory(inventoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(returnedDTO);

    }
}
