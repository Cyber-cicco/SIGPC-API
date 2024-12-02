package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import fr.diginamic.dto.UtilisateurDto;
import fr.diginamic.dto.CommentaireDto;

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
