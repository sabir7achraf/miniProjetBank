package org.example.miniprojet.Services;

import org.example.miniprojet.Dao.ClientDao;
import org.example.miniprojet.Entity.Client;
import org.example.miniprojet.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService  {
    @Autowired
    public ClientRepo clientRepo;

    @Autowired
    PasswordEncoder passwordEncoder;
    public void CreateClient(ClientDao client) {
        Client client1=new Client();
        client1.setNom(client.getNom());
        client1.setCode(client.getCode());
        client1.setTelephone(client.getTelephone());
        client1.setEmail(client.getEmail());
        client1.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepo.save(client1);
    }

    public Optional<Client> getClient(Long id) {
        return clientRepo.findById(id);
    }
    public List<Client> getAllClient() {
        return clientRepo.findAll();
    }
}
