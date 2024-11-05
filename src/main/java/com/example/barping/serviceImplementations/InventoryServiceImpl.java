package com.example.barping.serviceImplementations;

import com.example.barping.entities.Inventory;
import com.example.barping.repositories.InventoryRepository;
import com.example.barping.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory findById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public List<Inventory> findItemsToRestock() {
        return inventoryRepository.findAll().stream()
                .filter(inventory -> inventory.getQuantity() <= inventory.getRestockThreshold())
                .toList();
    }
}
