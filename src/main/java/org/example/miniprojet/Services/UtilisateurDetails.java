package org.example.miniprojet.Services;

import org.example.miniprojet.repository.ClientRepo;
import org.example.miniprojet.repository.EmployeRepo;
import org.example.miniprojet.repository.SupEmployeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetails implements UserDetailsService {
    @Autowired
    ClientRepo clientRepository;
    @Autowired
    EmployeRepo employeRepository;

    @Autowired
    SupEmployeRepo supEmployeRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientRepository.findClientByEmail(email)
                .or(() -> employeRepository.findEmployeByEmail(email))
                .or(() -> supEmployeRepository.findSupEmployeByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + email));
    }
}
