package fr.diginamic.projet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fr.diginamic.entities.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

/** Cette classe représente l'entité <b>Projet</b> */
@NoArgsConstructor
// @AllArgsConstructor
@Getter
@Setter
@Entity
// @Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "projet")
public class Projet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** Nom du projet */
  @Column(length = 100, nullable = false, unique = true)
  @NotNull(message = "Le nom du projet est requis - ENTITÉ")
  private String nom;

  /** Description du projet */
  @Column(length = 512)
  private String description;

  /** Date de début du projet - Requise */
  @Column(nullable = false)
  private LocalDate dateDebut;

  /** Date de fin du projet - Facultative */
  @Column private LocalDate dateFin;

  /** Date de fin estimée du projet - Facultative */
  @Column private LocalDate dateFinEstimee;

  /** Email de contact du projet - Requise */
  @Column(nullable = false)
  private String contact;

  public Projet(String nom, LocalDate dateDebut, String contact) {
    this.nom = nom;
    this.dateDebut = dateDebut;
    this.contact = contact;
  }

  @JsonIgnoreProperties("projets")
  @ManyToMany
  @JoinTable(
      name = "projet_commentaire",
      joinColumns = @JoinColumn(name = "projet_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "commentaire_id", referencedColumnName = "id"))
  private List<Commentaire> commentaires;

  @JsonIgnoreProperties("equipe")
  @ManyToOne
  @JoinColumn(name = "equipe_id")
  private Equipe equipe;

  @JsonIgnoreProperties("projet")
  @OneToMany(mappedBy = "projet")
  private List<Evenement> evenements;

  @OneToMany(mappedBy = "projet")
  private List<Sprint> sprint;

  @OneToMany(mappedBy = "projet")
  private List<Ressource> ressources;

  @ManyToOne
  @JoinColumn(name = "admin_id")
  private Utilisateur admin;

  @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProjetUtilisateur> projetUtilisateurs;
}
