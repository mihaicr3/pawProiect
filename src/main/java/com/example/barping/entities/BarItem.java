package com.example.barping.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
public class BarItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String category;
    @Setter
    @Getter
    private BigDecimal unitPrice;
    @Setter
    @Getter
    private String unit; // e.g., ml, bottle, etc.

    // Getters and Setters
}
