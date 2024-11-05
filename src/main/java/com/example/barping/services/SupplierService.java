package com.example.barping.services;

import com.example.barping.entities.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> findAll();
    Supplier findById(Long id);
    Supplier save(Supplier supplier);
    void deleteById(Long id);
}

