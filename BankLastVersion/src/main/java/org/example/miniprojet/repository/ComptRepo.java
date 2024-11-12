package org.example.miniprojet.repository;

import org.example.miniprojet.Entity.Client;
import org.example.miniprojet.Entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComptRepo extends JpaRepository<Compte,Long> {
    public Compte findByNumCompte(String code);
    public List<Compte> findAllByClient(Optional<Client> client);
}
