package org.example.miniprojet.Dao;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SupEmployeDao {
    Long id;
    String nom;
    String prenom;
    String email;
    String password ;
}
