package com.example.barping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
public class ClientOrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_order_id", nullable = false)
    @Getter
    @Setter
    private ClientOrder clientOrder;

    @ManyToOne
    @JoinColumn(name = "bar_item_id", nullable = false)
    @Getter
    @Setter
    private BarItem barItem;

    @Getter
    @Setter
    private int quantity;

    @Getter
    @Setter
    private BigDecimal unitPrice;

    @Getter
    @Setter
    private BigDecimal lineTotal;

    // Additional methods or validations can be added here
}
