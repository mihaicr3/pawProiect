package com.example.barping.serviceImplementations;

import com.example.barping.entities.ClientOrder;
import com.example.barping.repositories.ClientOrderRepository;
import com.example.barping.services.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    @Autowired
    private ClientOrderRepository clientOrderRepository;

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
    public void deleteById(Long id) {
        clientOrderRepository.deleteById(id);
    }
}
