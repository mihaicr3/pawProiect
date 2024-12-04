package com.example.barping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "bar_item_id", referencedColumnName = "id")
    private BarItem barItem;

    @Setter
    @Getter
    private int quantity;
    @Setter
    @Getter
    private int restockThreshold;

    // Getters and Setters
}
