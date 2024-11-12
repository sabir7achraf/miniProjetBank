package org.example.miniprojet.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    String numCompte;
    Double solde;
    Date dateCreation;

    @ManyToOne
    Client client;

    @ManyToOne
    Employe employe;

    @OneToMany
    List<Operation> operationList;
}
