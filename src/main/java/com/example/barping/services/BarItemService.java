package com.example.barping.services;

import com.example.barping.entities.BarItem;

import java.util.List;

public interface BarItemService {
    List<BarItem> findAll();
    BarItem findById(Long id);
    BarItem save(BarItem barItem);
    void deleteById(Long id);
}
