package com.rentconnect.demo.auth;


import com.rentconnect.demo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;

}
