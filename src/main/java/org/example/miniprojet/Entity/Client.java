package org.example.miniprojet.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor

public class Client extends  GrandUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long id;
    String nom;
    Long code;
    String telephone;


    @Override
    public String getRole() {
        return "USER_CLIENT";
    }

    @Override
    public String getUsername() {
        return email;
    }
}
