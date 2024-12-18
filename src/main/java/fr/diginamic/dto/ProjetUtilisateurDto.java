package fr.diginamic.dto;

import fr.diginamic.utilisateur.UtilisateurDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import fr.diginamic.projet.ProjetDto;

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
