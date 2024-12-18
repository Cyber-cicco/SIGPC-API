package fr.diginamic.dto;

import fr.diginamic.userstory.UserStoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import fr.diginamic.projet.ProjetDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SprintDto {

  private Long id;
  private LocalDateTime dateDebut;
  private LocalDateTime dateFin;
  private Integer sprintParJour;
  private ProjetDto projet;
  private List<UserStoryDto> userStories;
}
