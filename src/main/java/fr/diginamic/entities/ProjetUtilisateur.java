package fr.diginamic.entities;

import fr.diginamic.entities.enums.ProjetRoleEnum;
import fr.diginamic.projet.Projet;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class ProjetUtilisateur {

  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private ProjetRoleEnum role;

  @ManyToOne
  @JoinColumn(name = "utilisateur_id")
  private Utilisateur utilisateur;

  @ManyToOne
  @JoinColumn(name = "projet_id")
  private Projet projet;
}
