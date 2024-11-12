package org.example.miniprojet.Dao;
import lombok.*;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientDao {
    String nom;
    Long code;
    String telephone;
    String email;
    String password;
}
