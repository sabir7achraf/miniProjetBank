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
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    String nomEmploye;
    Long codeEmploye;


}
