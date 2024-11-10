package org.example.miniprojet.Dao;
import lombok.*;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientDao {
    public Long id;
    String nom;
    Long code;
    String telephone;
    String email;
    String password;
}
