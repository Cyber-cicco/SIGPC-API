package fr.diginamic.entities;

import fr.diginamic.projet.Projet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Evenement {

  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime date;
  private String nom;
  private String type;

  @ManyToOne
  @JoinColumn(name = "projet_id")
  private Projet projet;

  @ManyToOne
  @JoinColumn(name = "sprint_id")
  private Sprint sprint;
}
