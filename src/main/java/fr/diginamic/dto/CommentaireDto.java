package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import fr.diginamic.projet.ProjetDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentaireDto {

  private Long id;
  private String contenu;
  private CommentaireDto responseA;
  private List<CommentaireDto> responses;
  private ProjetDto projet;
  private UtilisateurDto utilisateur;
  private List<ReactionDto> reactions;
}
