package com.rentconnect.demo.service;

import com.rentconnect.demo.model.Inventory;
import com.rentconnect.demo.dto.InventoryDTO;
import com.rentconnect.demo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public List<Inventory> allInventories() {
        return inventoryRepository.findAll();
    }

    public InventoryDTO addInventory(InventoryDTO inventoryDTO ){

        inventoryRepository.save(getInventoryFromTDO(inventoryDTO));
        return inventoryDTO;
    }

    private Inventory getInventoryFromTDO( InventoryDTO inventoryDTO)
    {
        Inventory inventory = new Inventory();

        inventory.setChair(inventoryDTO.getChair());
        inventory.setAirConditioner(inventoryDTO.getAirConditioner());
        inventory.setBed(inventoryDTO.getBed());
        inventory.setTv(inventoryDTO.getTv());
        inventory.setSofa(inventoryDTO.getSofa());
        inventory.setMasa(inventoryDTO.getMasa());

        return inventory;
    }

    private InventoryDTO getTDOFromInventory ( Inventory inventory)
    {
        return new InventoryDTO();

    }


}
