package com.example.barping.serviceImplementations;

import com.example.barping.entities.ClientOrderDetail;
import com.example.barping.repositories.ClientOrderDetailRepository;
import com.example.barping.services.ClientOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientOrderDetailServiceImpl implements ClientOrderDetailService {

    @Autowired
    private ClientOrderDetailRepository clientOrderDetailRepository;

    @Override
    public List<ClientOrderDetail> findAll() {
        return clientOrderDetailRepository.findAll();
    }

    @Override
    public ClientOrderDetail findById(Long id) {
        return clientOrderDetailRepository.findById(id).orElse(null);
    }

    @Override
    public ClientOrderDetail save(ClientOrderDetail clientOrderDetail) {
        return clientOrderDetailRepository.save(clientOrderDetail);
    }

    @Override
    public void deleteById(Long id) {
        clientOrderDetailRepository.deleteById(id);
    }
}
