package com.example.barping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @OneToOne
    private BarItem barItem;
    @Setter
    @Getter
    private int quantity;
    @Setter
    @Getter
    private int restockThreshold;

    // Getters and Setters
}
