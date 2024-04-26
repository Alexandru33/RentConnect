package com.rentconnect.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String address;
    private Integer rooms;
    private Double price;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "property")
    private Set<Contract> contracts;

    @OneToMany(mappedBy = "property")
    private Set<Viewing> viewings;





}
