package org.example.miniprojet.Services;

import org.example.miniprojet.Dao.CompteDao;
import org.example.miniprojet.Entity.*;
import org.example.miniprojet.Exception.UsernameNotFoundException;
import org.example.miniprojet.repository.ClientRepo;
import org.example.miniprojet.repository.ComptRepo;
import org.example.miniprojet.repository.EmployeRepo;
import org.example.miniprojet.repository.OperationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComptService {
    @Autowired
    public ComptRepo compteRepo;

    @Autowired
    public EmployeRepo employeRepo;

    @Autowired
    public ClientRepo clientRepo;

    public OperationRepo opRepo;


    public void  CreateCompte(CompteDao compte, Long clientId, Long employeId) throws UsernameNotFoundException {
        Compte compte1 = new Compte();
        compte1.setNumCompte(compte.getNumCompte());
        compte1.setDateCreation(compte.getDateCreation());
        compte1.setSolde(compte.getSolde());
        Employe employe1 =employeRepo.findById(employeId).get();
        compte1.setEmploye(employe1);
        Client client = clientRepo.findById(clientId).get();
        compte1.setClient(client);
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

        Employe employe = employeRepo.findBynomEmploye(emp);
        Operation op = new Operation();
        op.setDateOperation(new Date());
        op.setMontant(montant);
        op.setTypeOperation(TypeOperation.VIREMENT);
        op.setEmploye(employe);
        op.setDescription("le compte "+ compSend.getNumCompte()+" envoi un montant de "+montant +" vers le compte "+compRecu.getNumCompte());
        opRepo.save(op);

        compSend.getOperationList().add(op);
        compRecu.getOperationList().add(op);

        compSend.setSolde(compSend.getSolde() - montant);
        compRecu.setSolde(compRecu.getSolde() + montant);


        compteRepo.save(compRecu);
        compteRepo.save(compSend);
    }
    public Compte consulter( Long id) throws UsernameNotFoundException {
         return compteRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Compte not found"));
    }
    public List<Compte> consulterList(Long clientID){
        Optional<Client> cli= clientRepo.findById(clientID);
       return compteRepo.findAllByClient(cli);
    }

}
