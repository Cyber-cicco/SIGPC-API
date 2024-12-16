package fr.diginamic.projet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.diginamic.dto.CommentaireDto;
import fr.diginamic.dto.EquipeDto;
import fr.diginamic.dto.EvenementDto;
import fr.diginamic.dto.ProjetUtilisateurDto;
import fr.diginamic.dto.RessourceDto;
import fr.diginamic.dto.SprintDto;
import fr.diginamic.validation.annotations.CustomLocalDate;
import fr.diginamic.validation.annotations.EndDateAfterStartDate;
import fr.diginamic.validation.deserializers.LocalDateDeserializer;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EndDateAfterStartDate.List({
  @EndDateAfterStartDate(
      startDateField = "dateDebut",
      endDateField = "dateFin",
      message = "La date de fin doit être après la date de début"),
  @EndDateAfterStartDate(
      startDateField = "dateDebut",
      endDateField = "dateFinEstimee",
      message = "La date de fin estimée doit être après la date de début")
})
public class ProjetDto {

  private Long id;

  @NotNull(message = "Le nom du projet ne doit pas être null")
  @NotBlank(message = "Le nom du projet ne doit pas être vide")
  @Size(min = 5, max = 100, message = "Le nom du projet doit avoir entre 5 et 100 caractères")
  @UniqueProjectName
  private String nom;

  @Size(max = 512, message = "La description doit faire moins de 512 caractères")
  private String description;

  @NotNull(message = "La date de début est requise")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @CustomLocalDate
  private LocalDate dateDebut;

  private LocalDate dateFin;

  private LocalDate dateFinEstimee;

  @NotNull(message = "L'email de contact est requis")
  @Email(message = "Le contact doit être un e-mail valide")
  private String contact;

  private List<CommentaireDto> commentaires;
  private EquipeDto equipe;
  private List<EvenementDto> evenements;
  private List<SprintDto> sprint;
  private List<RessourceDto> ressources;
  private List<ProjetUtilisateurDto> projetUtilisateurs;

  @Override
  public String toString() {
    return "ProjetDto{"
        + "nom='"
        + nom
        + '\''
        + ", description='"
        + description
        + '\''
        + ", dateDebut='"
        + dateDebut
        + '\''
        + ", dateFin='"
        + dateFin
        + '\''
        + ", dateFinEstimee='"
        + dateFinEstimee
        + '\''
        + ", contact='"
        + contact
        + '\''
        + '}';
  }
}
