package com.example.barping.serviceImplementations;

import com.example.barping.entities.ClientOrder;
import com.example.barping.entities.User;
import com.example.barping.repositories.ClientOrderRepository;
import com.example.barping.repositories.UserRepository;
import com.example.barping.services.ClientOrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    @Autowired
    private ClientOrderRepository clientOrderRepository;
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ClientOrder> findAll() {
        return clientOrderRepository.findAll();
    }

    @Override
    public ClientOrder findById(Long id) {
        return clientOrderRepository.findById(id).orElse(null);
    }

    @Override
    public ClientOrder save(ClientOrder clientOrder) {
        return clientOrderRepository.save(clientOrder);
    }


    @Override
    public List<ClientOrder> findIncompleteOrders() {
        return clientOrderRepository.findByOrderCompletion(0); // Use the repository method
    }
    @Override
    public List<ClientOrder> findDeliverablesOrders() {
        return clientOrderRepository.findByOrderCompletion(1); // Use the repository method
    }

    @Override
    public List<ClientOrder> findActiveOrdersByUserId() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.findByUsername(username);
        List<ClientOrder> orders=clientOrderRepository.findActiveOrdersByUserIdAndOrderCompletionIn(user.getId(), Arrays.asList(0, 1));
        orders.addAll(clientOrderRepository.findActiveOrdersByUserIdAndOrderCompletionIn(user.getId(),Arrays.asList(0, 1)));
        return clientOrderRepository.findActiveOrdersByUserIdAndOrderCompletionIn(user.getId(),Arrays.asList(0, 1));

    }

    @Override
    public void updateOrderCompletion(Long orderId, int completion) {
        clientOrderRepository.updateOrderCompletion(orderId, completion);
    }
    public List<ClientOrder> findByOrderDateBetween(LocalDate startDate, LocalDate endDate) {
        String jpql = "SELECT c FROM ClientOrder c WHERE c.orderDate BETWEEN :startDate AND :endDate";
        TypedQuery<ClientOrder> query = entityManager.createQuery(jpql, ClientOrder.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        clientOrderRepository.deleteById(id);
}
    }
