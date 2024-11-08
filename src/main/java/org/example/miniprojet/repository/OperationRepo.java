package org.example.miniprojet.repository;

import org.example.miniprojet.Entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepo extends JpaRepository<Operation, Long> {
}
