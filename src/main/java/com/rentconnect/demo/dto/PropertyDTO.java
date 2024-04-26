package com.rentconnect.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class PropertyDTO {

    private String address;
    private Integer rooms;
    private Double price;

}
