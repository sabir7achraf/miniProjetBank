package org.example.miniprojet.repository;

import org.example.miniprojet.Entity.GrandUser;
import org.example.miniprojet.Entity.SupEmploye;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupEmployeRepo extends JpaRepository<SupEmploye,Long> {
    public Optional<GrandUser> findSupEmployeByEmail(String email);

}
