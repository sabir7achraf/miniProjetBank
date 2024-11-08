package org.example.miniprojet.Services;

import org.example.miniprojet.Entity.Employe;
import org.example.miniprojet.Exception.UsernameNotFoundException;
import org.example.miniprojet.repository.EmployeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeService {
    @Autowired
    EmployeRepo employeRepo;

    public void CreatEmploye(Employe employe){
        employeRepo.save(employe);
    }

    public Employe getEmploye(Long id) throws UsernameNotFoundException {
           return employeRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Employe does not exist"));
    }
}
