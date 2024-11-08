package org.example.miniprojet.Dao;

import lombok.*;

import java.util.Date;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompteDao {
    Long id;
    String numCompte;
    Double solde;
    Date dateCreation;

}
