package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import fr.diginamic.dto.UtilisateurDto;
import fr.diginamic.dto.ProjetDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjetUtilisateurDto {

    private Long id;
    private String role;
    private UtilisateurDto utilisateur;
    private ProjetDto projet;

}
