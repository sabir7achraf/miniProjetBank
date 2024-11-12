package org.example.miniprojet.Controller;

import org.example.miniprojet.Entity.Client;
import org.example.miniprojet.Entity.Compte;
import org.example.miniprojet.Entity.Employe;
import org.example.miniprojet.Entity.Groupe;
import org.example.miniprojet.Exception.UsernameNotFoundException;
import org.example.miniprojet.Request.CreateCompteRequest;
import org.example.miniprojet.Request.EmpToGrpRequest;
import org.example.miniprojet.Request.OperationRequest;
import org.example.miniprojet.Request.VirmentRequest;
import org.example.miniprojet.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class bank {

    @Autowired
   ClientService clientService;

    @Autowired
   EmployeService employeService;

    @Autowired
   GroupService groupService;

    @Autowired
   ComptService comptService;



   @PostMapping("/createCompte")
    public String createCompte(@RequestBody CreateCompteRequest creatObj) throws UsernameNotFoundException {
       comptService.CreateCompte(creatObj.getCompte(), creatObj.getClientId(), creatObj.getEmployeId());
       return  "Your compte is created";
   }

   @PostMapping("/createEmploye")
    public String createEmploye(@RequestBody Employe  employe) {
       employeService.CreatEmploye(employe);
       return "Your employee has been created";
   }

   @PostMapping("/createClient")
    public String createClient(@RequestBody Client cli) {
       clientService.CreateClient(cli);
       return "Your client has been created";
   }

    @PostMapping("/createGroup")
    public String createGroup(@RequestBody Groupe grp) {
        groupService.CreateGroup(grp);
        return "Your client has been created";
    }

    @PostMapping ("/affecterEmpToGrp")
    public String affecterEmpToGrp(@RequestBody EmpToGrpRequest request) throws UsernameNotFoundException {
       groupService.affectEmployeToGroup(request.getNomGrp(),request.getNomEmp());
       return "Your employe has been affected to "+request.getNomEmp();
    }

    @PostMapping("/versement")
    public String versement( @RequestBody OperationRequest versement){
    comptService.Versement(versement.getNumCompte(),versement.getNomEmploye(), versement.getMontant());
       return "Your Operation 'versment' has been validated";
    }

    @PostMapping("/retrait")
    public String retrait( @RequestBody OperationRequest retrait){
        comptService.retrait(retrait.getNumCompte(),retrait.getNomEmploye(), retrait.getMontant());
        return "Your Operation 'retrait' has been validated";
    }

    @PostMapping("/virment")
    public String virment(@RequestBody VirmentRequest virmentRequest){
       comptService.virement(virmentRequest.getNumCompteSend(),virmentRequest.getNumCompteRecu(),virmentRequest.getMontant(),virmentRequest.getNomEmploye());
       return " votre virment vers le compte "+ virmentRequest.getNumCompteRecu() +" est validated";
    }

    @GetMapping("/compte/{Id}")
        public Compte consulter(@PathVariable Long Id) throws UsernameNotFoundException {
        return comptService.consulter(Id);
    }

    @GetMapping("/comptes/{clientId}")
        public List<Compte> consulterComptes ( @PathVariable Long clientId){
        return comptService.consulterList(clientId);
    }

}
