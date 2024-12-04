package com.example.barping.services;

import com.example.barping.entities.ClientOrder;

import java.util.List;

public interface ClientOrderService {
    List<ClientOrder> findAll();
    ClientOrder findById(Long id);
    ClientOrder save(ClientOrder clientOrder);
    void deleteById(Long id);
}
