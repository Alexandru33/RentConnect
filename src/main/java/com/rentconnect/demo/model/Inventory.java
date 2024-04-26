package com.rentconnect.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Integer chair;
    private Integer sofa;
    private Integer masa;
    private Integer tv;
    private Integer bed;
    private Integer airConditioner;

    @OneToOne(mappedBy = "inventory")
    private User owner;


}
