package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = true)
    private LocalDate dateFin;

    @Column(nullable = true)
    private LocalDate finEstimee;

    @Column(nullable = true)
    private String contact;

    @ManyToMany
    @JoinTable(name = "projet_commentaires", joinColumns = @JoinColumn(name = "projet_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "commentaires_id", referencedColumnName = "id"))
    private List<Commentaire> commentaires;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @OneToMany(mappedBy = "projet")
    private List<Evenement> evenements;

    @OneToMany(mappedBy = "projet")
    private List<Sprint> sprint;

    @OneToMany(mappedBy = "projet")
    private List<Ressource> ressources;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Utilisateur admin;

    @OneToMany(mappedBy = "projet")
    private List<ProjetUtilisateur> projetUtilisateurs;

}
