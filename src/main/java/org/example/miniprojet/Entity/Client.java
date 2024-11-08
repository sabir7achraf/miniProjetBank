package org.example.miniprojet.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long id;
    String nom;
    Long code;
}
