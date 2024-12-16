package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import fr.diginamic.projet.ProjetDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EvenementDto {

  private Long id;
  private LocalDateTime date;
  private String nom;
  private String type;
  private ProjetDto projet;
  private SprintDto sprint;
}
