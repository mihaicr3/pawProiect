package com.example.barping.services;

import com.example.barping.entities.ClientOrderDetail;

import java.util.List;

public interface ClientOrderDetailService {
    List<ClientOrderDetail> findAll();
    ClientOrderDetail findById(Long id);
    ClientOrderDetail save(ClientOrderDetail clientOrderDetail);
    void deleteById(Long id);
}
