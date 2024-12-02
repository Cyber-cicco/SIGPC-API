package fr.diginamic.dto;

import fr.diginamic.entities.enums.AvancementEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import fr.diginamic.dto.DocumentDto;
import fr.diginamic.dto.UtilisateurDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserStoryDto {

    private Long id;
    private String libelle;
    private String code;
    private String description;
    private LocalDate dateDebut;
    private AvancementEnum avancement;
    private LocalDate dateFin;
    private List<BugDto> bugs;
    private List<DocumentDto> documents;
    private UtilisateurDto utilisateurAssigne;

}
