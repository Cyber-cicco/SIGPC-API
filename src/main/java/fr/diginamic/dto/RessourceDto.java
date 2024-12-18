package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RessourceDto {

  private Long id;
  private String nom;
  private String lien;
  private String lienMedia;
  private Long projetId;

}
