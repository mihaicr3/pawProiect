package com.example.barping.services;

import com.example.barping.entities.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> findAll();
    Inventory findById(Long id);
    Inventory save(Inventory inventory);
    void deleteById(Long id);
    List<Inventory> findItemsToRestock();
}
