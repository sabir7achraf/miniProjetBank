package org.example.miniprojet.repository;

import org.example.miniprojet.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
