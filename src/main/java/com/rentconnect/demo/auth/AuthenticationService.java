package com.rentconnect.demo.auth;

import com.rentconnect.demo.config.JwtService;
import com.rentconnect.demo.dto.UserDTO;
import com.rentconnect.demo.exception.UserAlreadyRegisteredException;
import com.rentconnect.demo.model.Role;
import com.rentconnect.demo.model.User;
import com.rentconnect.demo.repository.UserRepository;
import com.rentconnect.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationResponse register(RegisterRequest request) throws UserAlreadyRegisteredException {

        var userDTO = UserDTO.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole())
                .build();

        var user = userService.fromUserDTO(userDTO);


        // userul care incearca sa se inregistreze exista deja
        if ( repository.existsByEmail(request.getEmail()))
        {
            throw new UserAlreadyRegisteredException();
        }
        repository.save(user);

        //var jwtToken = jwtService.generateToken(user);
        //return AuthenticationResponse.builder().token(jwtToken).build();
        return AuthenticationResponse.builder().build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail() ,
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        Role role = user.getRole();
        Map<String, Object> roleClaim = new HashMap<>();
        roleClaim.put("Role" , role);

        var jwtToken = jwtService.generateToken(roleClaim, user);

        return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
