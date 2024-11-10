package org.example.miniprojet.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employe extends GrandUser   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    String nomEmploye;
    Long codeEmploye;



    @Override
    public String getRole() {
        return "USER_EMPLOYE";
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
