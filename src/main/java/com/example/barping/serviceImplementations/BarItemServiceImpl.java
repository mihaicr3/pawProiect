package com.example.barping.serviceImplementations;

import com.example.barping.entities.BarItem;
import com.example.barping.repositories.BarItemRepository;
import com.example.barping.services.BarItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarItemServiceImpl implements BarItemService {

    @Autowired
    private BarItemRepository barItemRepository;

    @Override
    public List<BarItem> findAll() {
        return barItemRepository.findAll();
    }

    @Override
    public BarItem findById(Long id) {
        return barItemRepository.findById(id).orElse(null);
    }

    @Override
    public BarItem save(BarItem barItem) {
        return barItemRepository.save(barItem);
    }

    @Override
    public void deleteById(Long id) {
        barItemRepository.deleteById(id);
    }
}

