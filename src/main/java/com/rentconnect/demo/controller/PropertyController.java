package com.rentconnect.demo.controller;

import com.rentconnect.demo.dto.PropertyDTO;
import com.rentconnect.demo.exception.RoleException;
import com.rentconnect.demo.model.Property;
import com.rentconnect.demo.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/property")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/all")
    public ResponseEntity<List<PropertyDTO>> getAllProperties(
            @RequestHeader (name = "Authorization") String token
    )
    {
        List<Property> propertyList = propertyService.allProperties();
        List<PropertyDTO> propertyDTOList = new ArrayList<>();
        for (Property p: propertyList) {
            propertyDTOList.add(propertyService.toPropertyDTO(p));

        }
        return ResponseEntity.ok(  propertyDTOList );

    }

    @GetMapping("/personal")
    public ResponseEntity<List<PropertyDTO>> getPersonalProperties(
            @RequestHeader (name = "Authorization") String token
    )
    {
        List<Property> propertyList = propertyService.personalProperties(token.substring(7));
        List<PropertyDTO> propertyDTOList = new ArrayList<>();
        for (Property p: propertyList) {
            propertyDTOList.add(propertyService.toPropertyDTO(p));

        }
        return ResponseEntity.ok(  propertyDTOList );
    }

    @PostMapping("/add_property")
    public ResponseEntity<PropertyDTO> addProperty(
            @RequestBody PropertyDTO propertyDTO,
            @RequestHeader (name = "Authorization") String token
    )
    {
        Property property = propertyService.fromPropertyDTO(propertyDTO);
        try
        {
            propertyService.addProperty(property, token.substring(7));
            return ResponseEntity.ok(propertyDTO);

        }catch(RoleException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }

}
