package com.example.barping.repositories;

import com.example.barping.entities.ClientOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientOrderDetailRepository extends JpaRepository<ClientOrderDetail, Long> {
}
