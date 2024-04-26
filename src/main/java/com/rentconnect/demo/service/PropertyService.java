package com.rentconnect.demo.service;

import com.rentconnect.demo.config.JwtService;
import com.rentconnect.demo.dto.PropertyDTO;
import com.rentconnect.demo.exception.RoleException;
import com.rentconnect.demo.model.Property;
import com.rentconnect.demo.model.Property;
import com.rentconnect.demo.model.Role;
import com.rentconnect.demo.model.User;
import com.rentconnect.demo.repository.PropertyRepository;
import com.rentconnect.demo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    public List<Property> allProperties()
    {
        return propertyRepository.findAll();
    }


    public Property fromPropertyDTO(PropertyDTO PropertyDTO)
    {
        Property property = new Property();

        property.setAddress(PropertyDTO.getAddress());
        property.setRooms(PropertyDTO.getRooms());
        property.setPrice(PropertyDTO.getPrice());

        return property;

    }

    public PropertyDTO toPropertyDTO( Property property)
    {
        PropertyDTO propertyDTO = new PropertyDTO();

        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setRooms(property.getRooms());
        propertyDTO.setPrice(property.getPrice());


        return propertyDTO;

    }


    public List<Property> personalProperties(String token) {

        Role role = jwtService.extractRole(token);
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow();
        System.out.println(email);

        return propertyRepository.personalProperties(user.getId());
    }

    public Property addProperty(Property property, String token) throws RoleException {

        Role role = jwtService.extractRole(token);
        String email = jwtService.extractUsername(token);


        if( role == Role.TENANT)
        {
            throw new RoleException("TENANT role cannot add properties!");
        }

        property.setUser( userRepository.findByEmail(email).orElseThrow());


        propertyRepository.save(property);
        return property;

    }
}
