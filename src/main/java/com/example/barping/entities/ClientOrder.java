package com.example.barping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private int orderCompletion;



    @Getter
    @Setter
    private LocalDate orderDate;

    @Getter
    @Setter
    private String clientName;

    @Getter
    @Setter
    private String clientContact;

    @OneToMany(mappedBy = "clientOrder", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Set<ClientOrderDetail> orderDetails;

    @Getter
    @Setter
    private BigDecimal totalAmount;

    // Additional methods or validations can be added here
}
