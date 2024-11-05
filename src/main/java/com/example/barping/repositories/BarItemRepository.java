package com.example.barping.repositories;

import com.example.barping.entities.BarItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarItemRepository extends JpaRepository<BarItem, Long> {
}
