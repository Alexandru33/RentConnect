package com.rentconnect.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Viewing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Property property;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;
}
