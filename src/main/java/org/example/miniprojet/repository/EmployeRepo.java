package org.example.miniprojet.repository;

import org.example.miniprojet.Entity.Employe;
import org.example.miniprojet.Entity.GrandUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface EmployeRepo extends JpaRepository<Employe,Long> {
    public Employe findBynomEmploye(String nom);
    public Optional<GrandUser> findEmployeByEmail(String email);
}
