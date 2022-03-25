package com.youcode.testing.repository;

import com.youcode.testing.entity.Client;
import com.youcode.testing.enumeration.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ClientRepositoryTest {

    @Mock
    private ClientRepository clientRepository;

    Client client = new Client(1L,"a@a.com",123456,"John",23, Gender.MALE,true);

    @Test
    void getAllClients() {

        Client client2 = new Client(2L,"a@a.com",123456,"Doe",23, Gender.MALE,true);

        List<Client> clients = new ArrayList<>();
        clients.add(client);
        clients.add(client2);
        Mockito.when(clientRepository.findAll()).thenReturn(clients);
        assertThat(clientRepository.findAll()).isNotNull();
    }

    @Test
    void addClient() {

        Mockito.when(clientRepository.save(client))
                .thenReturn(client);
        assertThat(clientRepository.save(client)).isEqualTo(client);
    }

    @Test
    void deleteClient() {

        clientRepository.deleteById(client.getId());
        assertThat(clientRepository.getById(client.getId())).isNotEqualTo(client);
    }

    @Test
    void findById() {
        Mockito.when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        assertThat(clientRepository.findById(client.getId())).isNotNull();
    }

    @Test
    void findClientByEmail() {
        Mockito.when(clientRepository.findClientByEmail(client.getEmail())).thenReturn(Optional.of(client));
        assertThat(clientRepository.findClientByEmail(client.getEmail())).isNotNull();
    }

    @Test
    void findClientByGender() {
        Mockito.when(clientRepository.findClientByGender(client.getGender())).thenReturn(Optional.of(client));
        assertThat(clientRepository.findClientByGender(client.getGender())).isNotNull();
    }
}