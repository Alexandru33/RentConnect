package com.rentconnect.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;


    @Enumerated(EnumType.STRING)
    private Role role;


    private String email;
    private String password;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id" , referencedColumnName = "id")
    private Inventory inventory;


    @OneToMany(mappedBy = "user")
    private Set<Property> properties;

    @OneToMany(mappedBy = "user")
    private Set<Contract> contracts;

    @OneToMany(mappedBy = "user")
    private Set<Viewing> viewings;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
