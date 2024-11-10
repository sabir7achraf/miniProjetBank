package org.example.miniprojet.Dao;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeDao {
    Long id;
    String nom;
    String email;
    String password;
   Long codeEmploye;
}
