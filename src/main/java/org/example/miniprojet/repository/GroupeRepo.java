package org.example.miniprojet.repository;

import org.example.miniprojet.Entity.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupeRepo extends JpaRepository<Groupe, Long> {
    public Optional<Groupe> findByNomGroup(String nom);
}
