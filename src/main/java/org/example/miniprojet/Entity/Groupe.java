package org.example.miniprojet.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    String nomGroup;
    Long numGroup;

    @ManyToMany
    List<Employe> employees=new ArrayList<>();
}
