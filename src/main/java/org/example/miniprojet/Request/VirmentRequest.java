package org.example.miniprojet.Request;

import lombok.*;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VirmentRequest {

        String numCompteSend;
        String numCompteRecu;
        String  nomEmploye;
        Double montant;
    }


