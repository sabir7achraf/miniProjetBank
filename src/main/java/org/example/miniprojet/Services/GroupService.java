package org.example.miniprojet.Services;

import org.example.miniprojet.Dao.GroupeDao;
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

    public void CreateGroup(GroupeDao groupe){
        Groupe newGroupe = new Groupe();
        newGroupe.setNomGroup(groupe.getNomGroup());
        newGroupe.setNumGroup(groupe.getNumGroup());
        groupeRepo.save(newGroupe);
    }

    public void affectEmployeToGroup(String groupeNom, String employeNom) throws UsernameNotFoundException {
        Groupe grp = groupeRepo.findByNomGroup(groupeNom).orElseThrow(() -> new UsernameNotFoundException("groupe does not exist"));
        Employe employe = employeRepo.findBynomEmploye(employeNom);
        grp.getEmployees().add(employe);
        groupeRepo.save(grp);
    }
}
