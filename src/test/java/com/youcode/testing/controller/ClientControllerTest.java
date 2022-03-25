package com.youcode.testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youcode.testing.entity.Client;
import com.youcode.testing.enumeration.Gender;
import com.youcode.testing.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClientService clientService;

    Client client = new Client(1L,"a@a.com",123456,"John",23, Gender.MALE,true);

    @Test
    void getClients() throws Exception {
        Client client2 = new Client(2L,"a@a.com",123456,"Doe",23, Gender.MALE,true);

        List<Client> list = new ArrayList<>();

        list.add(client);
        list.add(client2);

        when(clientService.allClients()).thenReturn(list);
        mockMvc.perform(get("/api/v1/clients/all"))
                .andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {

        when(clientService.newClient(client))
                .thenReturn(client);

        mockMvc.perform(post("/api/v1/clients/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(client)))
                .andExpect(status().isOk());
    }

    @Test
    void getClientById() throws Exception {

        when(clientService.getClientById(client.getId())).thenReturn(java.util.Optional.of(client));
        mockMvc.perform(get("/api/v1/clients/" + client.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getClientByGender() throws Exception {
        when(clientService.getClientByGender(client.getGender())).thenReturn(java.util.Optional.of(client));
        mockMvc.perform(get("/api/v1/clients/all/" + client.getGender())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getClientByEmail() throws Exception {
        when(clientService.getClientByEmail(client.getEmail())).thenReturn(java.util.Optional.of(client));
        mockMvc.perform(get("/api/v1/clients/" + client.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}