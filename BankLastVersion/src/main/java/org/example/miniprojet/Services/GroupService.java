package org.example.miniprojet.Services;

import org.example.miniprojet.Entity.Employe;
import org.example.miniprojet.Entity.Groupe;
import org.example.miniprojet.Exception.UsernameNotFoundException;
import org.example.miniprojet.repository.EmployeRepo;
import org.example.miniprojet.repository.GroupeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    public GroupeRepo groupeRepo;

    @Autowired
    public EmployeRepo employeRepo;

    public void CreateGroup(Groupe groupe){
        groupeRepo.save(groupe);
    }
    public void affectEmployeToGroup(String groupeNom, String employeNom) throws UsernameNotFoundException {
        Groupe grp = groupeRepo.findByNomGroup(groupeNom).orElseThrow(() -> new UsernameNotFoundException("groupe does not exist"));
        Employe employe = employeRepo.findBynomEmploye(employeNom);
        grp.getEmployees().add(employe);

    }
}
