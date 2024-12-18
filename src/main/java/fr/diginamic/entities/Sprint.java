package fr.diginamic.entities;

import fr.diginamic.projet.Projet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Sprint {

  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date_debut", nullable = false)
  private LocalDateTime dateDebut;

  @Column(name = "date_fin", nullable = false)
  private LocalDateTime dateFin;

  @Column(name = "sprint_par_jour", nullable = true)
  private Integer sprintParJour;

  @ManyToOne
  @JoinColumn(name = "projet_id")
  private Projet projet;

  @ManyToMany
  @JoinTable(name = "sprint_userStories", joinColumns = @JoinColumn(name = "sprint_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "userStories_id", referencedColumnName = "id"))
  private List<UserStory> userStories;
}
