package org.example.miniprojet.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.miniprojet.Dao.CompteDao;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompteRequest {

        private CompteDao compte;
        private Long clientId;
        private Long employeId;
}
