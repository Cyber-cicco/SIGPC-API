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

  private LocalDateTime dateDebut;
  private LocalDateTime dateFin;
  private Integer sprintParJour;

  @ManyToOne
  @JoinColumn(name = "projet_id")
  private Projet projet;

  @ManyToMany
  @JoinTable(
      name = "sprint_userStories",
      joinColumns = @JoinColumn(name = "sprint_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "userStories_id", referencedColumnName = "id"))
  private List<UserStory> userStories;
}
