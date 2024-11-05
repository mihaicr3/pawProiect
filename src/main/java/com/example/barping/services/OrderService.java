package com.example.barping.services;

import com.example.barping.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Long id);
    Order save(Order order);
    void deleteById(Long id);
}
