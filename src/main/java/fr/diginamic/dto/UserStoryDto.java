package fr.diginamic.dto;

import fr.diginamic.entities.enums.AvancementEnum;
import fr.diginamic.utilisateur.UtilisateurDto;
import fr.diginamic.validation.annotations.EndDateAfterStartDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EndDateAfterStartDate(
        startDateField = "dateDebut",
        endDateField = "dateFin",
        message = "La date de fin ne peut être avant la date de début"
)
@EndDateAfterStartDate(
        startDateField = "dateDebut",
        endDateField = "finEstime",
        message = "La date de fin estimée ne peut être avant la date de début"
)
public class UserStoryDto {

    private Long id;
    @NotBlank
    @Size(
            min = 3,
            max = 60,
            message = "La taille du libelle doit être comprise entre 3 et 60 caractères"
    )
    private String libelle;
    private String code;
    @NotBlank
    @Size(min = 5, max = 2048)
    private String description;
    @NotNull
    private LocalDate dateDebut;
    @NotNull
    private AvancementEnum avancement;
    private LocalDate dateFin;
    private LocalDate finEstime;
    @NotNull
    private Long projetId;
    private UtilisateurDto utilisateurAssigne;

}
