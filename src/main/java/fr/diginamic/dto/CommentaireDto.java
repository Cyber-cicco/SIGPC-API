package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import fr.diginamic.dto.CommentaireDto;
import fr.diginamic.dto.CommentaireDto;
import java.util.List;
import fr.diginamic.dto.ProjetDto;
import fr.diginamic.dto.UtilisateurDto;
import fr.diginamic.dto.ReactionDto;

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
