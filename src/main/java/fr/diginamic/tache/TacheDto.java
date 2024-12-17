package fr.diginamic.tache;

import java.time.LocalDate;
import java.util.Date;

import fr.diginamic.validation.annotations.EndDateAfterStartDate;
import fr.diginamic.validation.annotations.NullableTogether;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.cglib.core.Local;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EndDateAfterStartDate(
        startDateField = "dateDebut",
        endDateField = "dateFin",
        message = "La date de fin doit obligatoirement se trouver après la date de début"
)
@EndDateAfterStartDate(
        startDateField = "dateDebut",
        endDateField = "finEstime",
        message = "La date de fin estimée doit obligatoirement se trouver après la date de début"
)
@NullableTogether(
        firstField = "dateDebut",
        secondField = "dateFin",
        message = "La date de fin ne peut être précisée sans une date de début"
)
@NullableTogether(
        firstField = "dateDebut",
        secondField = "finEstime",
        message = "La date de fin estimée ne peut être précisée sans une date de début"
)
public class TacheDto {

    private Long id;
    @NotBlank
    @Size(min = 3, max = 60)
    private String nom;
    @Size(max = 2048)
    private String description;
    private boolean done;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalDate finEstime;
    @NotNull
    private Long userStoryId;

}
