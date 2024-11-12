package org.example.miniprojet.Services;

import org.example.miniprojet.Dao.CompteDao;
import org.example.miniprojet.Entity.*;
import org.example.miniprojet.Exception.UsernameNotFoundException;
import org.example.miniprojet.repository.ClientRepo;
import org.example.miniprojet.repository.ComptRepo;
import org.example.miniprojet.repository.EmployeRepo;
import org.example.miniprojet.repository.OperationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComptService {
    @Autowired
    public  ComptRepo compteRepo;

    @Autowired
    public EmployeRepo employeRepo;

    @Autowired
    public ClientRepo clientRepo;

    @Autowired
    public OperationRepo opRepo;

    public List<Compte> getAllComptes() {
        return compteRepo.findAll();
    }


    public void  CreateCompte(CompteDao compte, Long clientId, Long employeId) throws UsernameNotFoundException {
        Compte compte1 = new Compte();
        compte1.setNumCompte(compte.getNumCompte());
        compte1.setDateCreation(compte.getDateCreation());
        compte1.setSolde(compte.getSolde());
        Employe employe1 =employeRepo.findById(employeId).get();
        compte1.setEmploye(employe1);
        Client client = clientRepo.findById(clientId).get();
        compte1.setClient(client);
        compteRepo.save(compte1);
    }
    public void Versement(String numCompte,String nomEmp,Double montant){
        Compte compte = compteRepo.findByNumCompte(numCompte);
        Employe emp = employeRepo.findBynomEmploye(nomEmp);
        Operation op = new Operation();
        op.setDateOperation(new Date());
        op.setMontant(montant);
        op.setTypeOperation(TypeOperation.VERSEMENT);
        op.setEmploye(emp);
        op.setDescription("versement de "+ montant);
        opRepo.save(op);
        compte.getOperationList().add(op);
        compte.setSolde(compte.getSolde()+montant);
        compteRepo.save(compte);
    }
    public void retrait(String numCompte,String nomEmp,Double montant){
        Compte compte = compteRepo.findByNumCompte(numCompte);
        if(compte.getSolde()>=montant) {
            Employe emp = employeRepo.findBynomEmploye(nomEmp);
            Operation op = new Operation();
            op.setDateOperation(new Date());
            op.setMontant(montant);
            op.setTypeOperation(TypeOperation.RETRAIT);
            op.setEmploye(emp);
            op.setDescription("retrait de "+ montant);
            opRepo.save(op);
            compte.getOperationList().add(op);
            compte.setSolde(compte.getSolde() - montant);
            compteRepo.save(compte);
        }
        else
            System.out.println("votre solde est inssufisante ");
    }

    public void virement(String send ,String recu,Double montant ,String emp){
        Compte compSend = compteRepo.findByNumCompte(send);
        Compte compRecu = compteRepo.findByNumCompte(recu);
        if(compSend.getSolde() >= montant) {
            Employe employe = employeRepo.findBynomEmploye(emp);

            // Opération pour le compte émetteur
            Operation opSend = new Operation();
            opSend.setDateOperation(new Date());
            opSend.setMontant(-montant);
            opSend.setTypeOperation(TypeOperation.VIREMENT);
            opSend.setEmploye(employe);
            opSend.setDescription("Débit pour le compte " + compSend.getNumCompte() + " vers le compte " + compRecu.getNumCompte());
            opRepo.save(opSend);
            compSend.getOperationList().add(opSend);

            // Opération pour le compte récepteur
            Operation opRecu = new Operation();
            opRecu.setDateOperation(new Date());
            opRecu.setMontant(montant);
            opRecu.setTypeOperation(TypeOperation.VIREMENT);
            opRecu.setEmploye(employe);
            opRecu.setDescription("Crédit pour le compte " + compRecu.getNumCompte() + " depuis le compte " + compSend.getNumCompte());
            opRepo.save(opRecu);
            compRecu.getOperationList().add(opRecu);

            // Mise à jour des soldes
            compSend.setSolde(compSend.getSolde() - montant);
            compRecu.setSolde(compRecu.getSolde() + montant);

            // Sauvegarde des comptes
            compteRepo.save(compSend);
            compteRepo.save(compRecu);
        } else
            System.out.println("Votre solde est insuffisant.");
    }
    public Compte consulter( Long id) throws UsernameNotFoundException {
         return compteRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Compte not found"));
    }
    public List<Compte> consulterList(Long clientID){
        Optional<Client> cli= clientRepo.findById(clientID);
       return compteRepo.findAllByClient(cli);
    }

}
