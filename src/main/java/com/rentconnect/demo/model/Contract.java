package com.rentconnect.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Property property;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    private LocalDate startDate;
    private LocalDate endDate;
    private String paymentMode;
    private Float price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Feedback feedback;

}
