package com.rentconnect.demo.auth;

import com.rentconnect.demo.exception.UserAlreadyRegisteredException;
import com.rentconnect.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController  {

    private final AuthenticationService authenticationService;

    @GetMapping("/all_users")
    public ResponseEntity<List<User>>  getAllUsers (
            )
    {
        return ResponseEntity.ok(authenticationService.getAllUsers());
    }


    @PostMapping("/register")
    public ResponseEntity register (
            @RequestBody RegisterRequest request
    )
    {
        try
        {
            return ResponseEntity.ok( authenticationService.register(request));

        }
        catch (UserAlreadyRegisteredException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    )
    {
        return ResponseEntity.ok( authenticationService.authenticate(request));

    }
}
