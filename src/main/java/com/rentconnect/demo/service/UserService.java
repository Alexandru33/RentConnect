package com.rentconnect.demo.service;

import com.rentconnect.demo.dto.UserDTO;
import com.rentconnect.demo.model.User;
import com.rentconnect.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    List<User>  allUsers () {
        return userRepository.findAll();
    }

    Boolean userByEmail( String email)
    {
        return userRepository.existsByEmail(email);

    }

    Boolean userByName ( String name )
    {
        return userRepository.existsByName(name);
    }

    public User fromUserDTO(UserDTO userDTO)
    {
        User user = new User();

        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());

        return user;

    }

    public UserDTO toUserDTO( User user)
    {
        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());

        return userDTO;

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();

    }
}
