package com.youcode.testing.controller;

import com.youcode.testing.entity.Client;
import com.youcode.testing.enumeration.Gender;
import com.youcode.testing.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;

    @GetMapping
    public Page<Client> getClients(@RequestParam(defaultValue = "0") int page) {
        logger.info("get clients list");
        return clientService.getClients(page);
    }

    @PostMapping("/save")
    public void save(@RequestBody Client client){
        clientService.newClient(client);
    }

    @GetMapping("{clientId:^\\d+$}")
    public Optional<Client> getClientById(@PathVariable("clientId") Long id) {
        Optional<Client> client = clientService.getClientById(id);

        if (client.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }

        return client;
    }

    @GetMapping("{email:.+@.+\\..+}")
    public Optional<Client> getClientByEmail(@PathVariable("email") String email) {
        return clientService.getClientByEmail(email);
    }

    @GetMapping("all/{gender}")
    public Optional<Client> getClientByGender(@PathVariable("gender") Gender gender) {
        return clientService.getClientByGender(gender);
    }

    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody Client client){
        clientService.updateClient(id,client);
    }
}
