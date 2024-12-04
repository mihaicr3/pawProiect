package com.example.barping.repositories;

import com.example.barping.entities.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {
}
