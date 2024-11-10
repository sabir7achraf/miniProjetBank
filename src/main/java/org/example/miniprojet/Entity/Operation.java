package org.example.miniprojet.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    Date dateOperation ;
    Long numOperation;
    Double Montant ;
    TypeOperation typeOperation;
    String description;

    @ManyToOne
    Employe employe;
}
