package org.example.miniprojet.Services;

import org.example.miniprojet.Dao.EmployeDao;
import org.example.miniprojet.Entity.Employe;
import org.example.miniprojet.Exception.UsernameNotFoundException;
import org.example.miniprojet.repository.EmployeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService {
    @Autowired
    EmployeRepo employeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void CreatEmploye(EmployeDao employe){
        Employe employe1 = new Employe();
        employe1.setCodeEmploye(employe.getCodeEmploye());
        employe1.setNomEmploye(employe.getNomEmploye());
        employe1.setEmail(employe.getEmail());
        employe1.setPassword(passwordEncoder.encode(employe.getPassword()));
        employeRepo.save(employe1);
    }

    public Employe getEmploye(Long id) throws UsernameNotFoundException {
           return employeRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Employe does not exist"));
    }

    public List<Employe> getAllEmployes() {
        return employeRepo.findAll();
    }
}
