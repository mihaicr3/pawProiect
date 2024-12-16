package com.example.barping.repositories;

import com.example.barping.entities.ClientOrder;
import com.example.barping.entities.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {
    List<ClientOrder> findByOrderCompletion(int orderCompletion);

    // Modified method to filter by both userId and orderCompletion values of 0 or 1
    List<ClientOrder> findActiveOrdersByUserIdAndOrderCompletionIn(long userId, List<Integer> orderCompletion);

    @Modifying
    @Transactional
    @Query("UPDATE ClientOrder c SET c.orderCompletion = :completion WHERE c.id = :orderId")
    void updateOrderCompletion(@Param("orderId") Long orderId, @Param("completion") int completion);
}
