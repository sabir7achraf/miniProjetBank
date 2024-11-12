package org.example.miniprojet.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompteEpargne extends  Compte{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    Double taux;
}
