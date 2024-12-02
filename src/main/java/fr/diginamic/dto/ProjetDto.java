package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import fr.diginamic.dto.CommentaireDto;
import java.util.List;
import fr.diginamic.dto.EquipeDto;
import fr.diginamic.dto.EvenementDto;
import fr.diginamic.dto.SprintDto;
import fr.diginamic.dto.RessourceDto;
import fr.diginamic.dto.ProjetUtilisateurDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjetDto {

    private Long id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalDate finEstimee;
    private String contact;
    private List<CommentaireDto> commentaires;
    private EquipeDto equipe;
    private List<EvenementDto> evenements;
    private List<SprintDto> sprint;
    private List<RessourceDto> ressources;
    private List<ProjetUtilisateurDto> projetUtilisateurs;

}
