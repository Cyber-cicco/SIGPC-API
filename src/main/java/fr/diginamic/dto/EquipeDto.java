package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import fr.diginamic.projet.ProjetDto;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EquipeDto {

  private Long id;
  private String nom;
  private String contact;
  private String description;
  private List<ProjetDto> projets;
  private UtilisateurDto admin;
  private List<InvitationDto> invitations;
}
