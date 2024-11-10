package org.example.miniprojet.repository;

import org.example.miniprojet.Entity.Client;
import org.example.miniprojet.Entity.GrandUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<GrandUser> findClientByEmail(String email);
}
