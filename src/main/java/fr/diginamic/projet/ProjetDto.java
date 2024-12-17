package fr.diginamic.projet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.diginamic.validation.annotations.CustomLocalDate;
import fr.diginamic.validation.annotations.EndDateAfterStartDate;
import fr.diginamic.validation.deserializers.LocalDateDeserializer;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
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

  //  @NotNull(message = "Le nom du projet ne doit pas être null", groups = NotNullGroup.class)
  //  @NotEmpty(message = "Le nom du projet ne doit pas être vide", groups = NotEmptyGroup.class)
  //  @NotBlank(
  //      message = "Le nom du projet ne doit pas avoir que des espaces",
  //      groups = NotBlankGroup.class)
  @NotNull(message = "Le nom du projet ne doit pas être null")
  //  @NotEmpty(message = "Le nom du projet ne doit pas être vide")
  @NotBlank(message = "Le nom du projet ne doit pas avoir que des espaces")
  @Size(min = 5, max = 100, message = "Le nom du projet doit avoir entre 5 et 100 caractères")
  @UniqueProjectName
  private String nom;

  @Size(max = 512, message = "La description doit faire moins de 512 caractères")
  private String description;

  @NotNull(message = "La date de début est requise")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @CustomLocalDate
  private LocalDate dateDebut;

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @CustomLocalDate
  private LocalDate dateFin;

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @CustomLocalDate
  private LocalDate dateFinEstimee;

  private Long equipeId;

  @NotNull(message = "L'email de contact est requis")
  @Email(message = "Le contact doit être un e-mail valide")
  private String contact;

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
