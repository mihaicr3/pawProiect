package com.example.barping.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="client_orders_details")
public class ClientOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ClientOrder clientOrder;

    @OneToOne
    private BarItem barItem;

    private float quantity;

    private double totalPrice;


    // Getters and Setters
}
