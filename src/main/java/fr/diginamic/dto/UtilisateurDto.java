package fr.diginamic.dto;

import fr.diginamic.projet.ProjetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UtilisateurDto {

  private Long id;
  private String email;
  private String nom;
  private String prenom;
  // TODO : supprimer ce champ, n'est là que pour le test
  private String activationLink;
  private boolean emailVerified;
  private String roles;
  private LocalDateTime dateEcheanceSuppression;
  private TentativeSupressionMdpDto tentativeSuppresionMdp;
  private List<InvitationDto> invitation;
  private List<ReactionDto> reactions;
  private List<ProjetDto> projets;
  private List<UtilisateurDto> commentaires;
  private List<EquipeDto> equipes;
}
