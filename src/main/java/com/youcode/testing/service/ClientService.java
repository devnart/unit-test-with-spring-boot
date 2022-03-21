package com.youcode.testing.service;

import com.youcode.testing.controller.ClientController;
import com.youcode.testing.entity.Client;
import com.youcode.testing.enumeration.Gender;
import com.youcode.testing.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Optional<Client> getClientByGender(Gender gender) {
        return clientRepository.findClientByGender(gender);
    }

    public void deleteClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "client not found");
        }

        client.get().setIsActive(false);
    }

    public void updateClient(Long id, Client client) {

        Client updateClient = clientRepository.getById(id);

        updateClient.setName(client.getName());
        updateClient.setEmail(client.getEmail());
        updateClient.setGender(client.getGender());
        updateClient.setPhone(client.getPhone());
        updateClient.setIsActive(client.getIsActive());

        clientRepository.save(updateClient);
    }
}






