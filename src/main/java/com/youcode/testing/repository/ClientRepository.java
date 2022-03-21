package com.youcode.testing.repository;

import com.youcode.testing.entity.Client;
import com.youcode.testing.enumeration.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {


    Optional<Client> findById(Long id);
    Optional<Client> findClientByEmail(String email);
    Optional<Client> findClientByGender(Gender gender);
}
