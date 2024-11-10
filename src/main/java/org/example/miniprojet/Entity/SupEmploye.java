package org.example.miniprojet.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class SupEmploye  extends  GrandUser{


    String nom;
    String prenom;


    @Override
    public String getRole() {
        return "USER_SUPEREMPLOYE";
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
