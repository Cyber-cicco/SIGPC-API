package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import fr.diginamic.utilisateur.UtilisateurDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReactionDto {

    private Long id;
    private String emoji;
    private UtilisateurDto utilisateur;
    private CommentaireDto commentaire;

}
