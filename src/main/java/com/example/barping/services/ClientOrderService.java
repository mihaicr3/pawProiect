package com.example.barping.services;

import com.example.barping.entities.ClientOrder;

import java.time.LocalDate;
import java.util.List;

public interface ClientOrderService {
    List<ClientOrder> findAll();
    ClientOrder findById(Long id);
    ClientOrder save(ClientOrder clientOrder);
    void deleteById(Long id);
    List<ClientOrder> findIncompleteOrders();
    List<ClientOrder> findDeliverablesOrders();
    List<ClientOrder> findActiveOrdersByUserId();
    void updateOrderCompletion(Long orderId, int completion);
    List<ClientOrder> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

}
