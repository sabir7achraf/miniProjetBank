package org.example.miniprojet.Request;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequest {
    String numCompte;
    String  nomEmploye;
    Double montant;
}
