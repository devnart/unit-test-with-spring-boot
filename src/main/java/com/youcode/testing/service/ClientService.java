package com.youcode.testing.service;

import com.youcode.testing.controller.ClientController;
import com.youcode.testing.entity.Client;
import com.youcode.testing.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    Logger logger = LoggerFactory.getLogger(ClientController.class);

    public final ClientRepository clientRepository;

    public Page<Client> getClients(int page) {
        Pageable paging = PageRequest.of(page, 10);
        return clientRepository.findAll(paging);
    }

    public void newClient(Client client) {
        Optional<Client> exist = clientRepository.findClientByEmail(client.getEmail());

        if(exist.isPresent()){
            logger.error("email taken");
            throw new IllegalStateException("email taken");
        }

        clientRepository.save(client);
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }
}
