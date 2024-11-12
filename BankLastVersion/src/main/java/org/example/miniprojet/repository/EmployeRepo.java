package org.example.miniprojet.repository;

import org.example.miniprojet.Entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepo extends JpaRepository<Employe,Long> {

    public Employe findBynomEmploye(String nom);
}
