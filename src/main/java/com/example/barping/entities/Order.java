package com.example.barping.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="suply_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Supplier supplier;

    @OneToOne
    private BarItem barItem;

    private int quantity;

    private Date orderDate;

    private boolean fulfilled;

    // Getters and Setters
}
