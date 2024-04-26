package com.rentconnect.demo.dto;

import com.rentconnect.demo.model.Inventory;
import com.rentconnect.demo.repository.InventoryRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryDTO {

    private Integer chair;
    private Integer sofa;
    private Integer masa;
    private Integer tv;
    private Integer bed;
    private Integer airConditioner;

    public InventoryDTO ( Inventory inventory)
    {


    }

}
