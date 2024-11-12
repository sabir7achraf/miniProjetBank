package org.example.miniprojet.Dao;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeDao {
    Long id;
    String nomEmploye;
    String email;
    String password;
   Long codeEmploye;
}
