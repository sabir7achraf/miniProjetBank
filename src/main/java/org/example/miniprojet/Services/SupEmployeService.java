package org.example.miniprojet.Services;

import org.example.miniprojet.Dao.SupEmployeDao;
import org.example.miniprojet.Entity.SupEmploye;
import org.example.miniprojet.repository.SupEmployeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SupEmployeService {

@Autowired
    SupEmployeRepo repo;

@Autowired
    PasswordEncoder passwordEncoder;
    public void CreateSupEmploye(SupEmployeDao sup) {
        SupEmploye emp = new SupEmploye();
        emp.setNom(sup.getNom());
        emp.setPrenom(sup.getPrenom());
        emp.setEmail(sup.getEmail());
        emp.setPassword(passwordEncoder.encode(sup.getPassword()));
        repo.save(emp);
    }
}
