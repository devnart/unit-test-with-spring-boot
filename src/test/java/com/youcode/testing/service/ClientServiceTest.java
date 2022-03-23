package com.youcode.testing.service;

import com.youcode.testing.entity.Client;
import com.youcode.testing.enumeration.Gender;
import com.youcode.testing.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientServiceTest {

    @InjectMocks
    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    Client client = new Client(1L,"a@a.com",123456,"John",23,Gender.MALE,true, LocalDateTime.now(),LocalDateTime.now());

    @Test
    void newClient() {
        Client client = new Client(1L,"a@a.com",123456,"John",23,Gender.MALE,true, LocalDateTime.now(),LocalDateTime.now());
        when(clientRepository.save(client)).thenReturn(client);
        assertEquals(client, clientService.newClient(client));
    }

    @Test
    void allClients() {
        Client client2 = new Client(
                2L,
                "a@a.com",
                123456,
                "John",
                23,
                Gender.MALE,
                true,
                LocalDateTime.now(),LocalDateTime.now());

        List<Client> list = new ArrayList<>();
        list.add(client);
        list.add(client2);
        // Pageable paging = PageRequest.of(0, );

        when(clientRepository.findAll()).thenReturn(list);
        assertEquals(2, clientService.allClients().size());
    }

    @Test
    void getClientById() {
        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(client));
        assertEquals(java.util.Optional.of(client), clientService.getClientById(1L));
    }

    @Test
    void getClientByEmail() {
        when(clientRepository.findClientByEmail("a@a.com")).thenReturn(java.util.Optional.of(client));
        assertEquals(java.util.Optional.of(client), clientService.getClientByEmail("a@a.com"));
    }

    @Test
    void getClientByGender() {
        when(clientRepository.findClientByGender(Gender.MALE)).thenReturn(java.util.Optional.of(client));
        assertEquals(java.util.Optional.of(client), clientService.getClientByGender(Gender.MALE));
    }
}