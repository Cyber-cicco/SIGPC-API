package fr.diginamic.entities;

import fr.diginamic.projet.Projet;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Ressource {

  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 255, nullable = true)
  private String nom;

  @Column(length = 255, nullable = true)
  private String lien;

  @Column(length = 255, nullable = true)
  private String lienMedia;

  @ManyToOne
  @JoinColumn(name = "projet_id")
  private Projet projet;
}
