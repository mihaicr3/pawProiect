package com.example.barping.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="client_order")
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;



    private int price;

    private Date orderDate;

    private boolean fulfilled;

    // Getters and Setters
}
