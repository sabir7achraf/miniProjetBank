package org.example.miniprojet.Services;

import org.example.miniprojet.Entity.Client;
import org.example.miniprojet.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService  {
    @Autowired
    public ClientRepo clientRepo;
    public void CreateClient(Client client) {
        clientRepo.save(client);
    }
}
