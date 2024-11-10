package org.example.miniprojet.Controller;

import org.example.miniprojet.Dao.*;
import org.example.miniprojet.Entity.Client;
import org.example.miniprojet.Entity.Compte;
import org.example.miniprojet.Entity.Employe;
import org.example.miniprojet.Entity.Groupe;
import org.example.miniprojet.Exception.UsernameNotFoundException;
import org.example.miniprojet.Request.CreateCompteRequest;
import org.example.miniprojet.Request.EmpToGrpRequest;
import org.example.miniprojet.Request.OperationRequest;
import org.example.miniprojet.Request.VirmentRequest;
import org.example.miniprojet.Security.JwtService;
import org.example.miniprojet.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class bank {

    @Autowired
   ClientService clientService;

    @Autowired
    SupEmployeService supEmployeService;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
   EmployeService employeService;

    @Autowired
   GroupService groupService;

    @Autowired
   ComptService comptService;

    @Autowired
    JwtService jwtService;



   @PostMapping("/createCompte")
    public String createCompte(@RequestBody CreateCompteRequest creatObj) throws UsernameNotFoundException {
       comptService.CreateCompte(creatObj.getCompte(), creatObj.getClientId(), creatObj.getEmployeId());
       return  "Your compte is created";
   }

   @PostMapping("/createEmploye")
    public String createEmploye(@RequestBody EmployeDao employe) {
       employeService.CreatEmploye(employe);
       return "Your employee has been created";
   }

    @PostMapping("/createSupEmploye")
    public String createSupEmploye(@RequestBody SupEmployeDao empl) {
        supEmployeService.CreateSupEmploye(empl);
        return "Your employee has been created";
    }


    @PostMapping("/createClient")
    public String createClient(@RequestBody ClientDao cli) {
       clientService.CreateClient(cli);
       return "Your client has been created";
   }

    @PostMapping("/createGroup")
    public String createGroup(@RequestBody GroupeDao grp) {
        groupService.CreateGroup(grp);
        return "Your Groupe has been created";
    }

    @PostMapping ("/affecterEmpToGrp")
    public String affecterEmpToGrp(@RequestBody EmpToGrpRequest request) throws UsernameNotFoundException {
       groupService.affectEmployeToGroup(request.getNomGrp(),request.getNomEmp());
       return "Your employe has been affected to "+request.getNomEmp();
    }

    @GetMapping("/client ")
    public List<Client> getClient() {
      return  clientService.getAllClient();
    }

    @GetMapping("/client/{id}")
    public Optional<Client> getClient(@PathVariable Long id ){
       return clientService.getClient(id);
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(),authRequest.password()));
        if(authenticate.isAuthenticated()){
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            String jwt = this.jwtService.generate(userDetails);
            Map<String, Object> response = new HashMap<>();
            response.put("token",jwt);
            return ResponseEntity.ok(response);
        }
        return null;
    }





}
