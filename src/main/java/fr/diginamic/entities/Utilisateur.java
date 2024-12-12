package fr.diginamic.entities;

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
public class Utilisateur {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private String email;
    private String nom;
    private String prenom;
    private boolean emailVerified;
    private String roles;
    private LocalDateTime dateEcheanceSuppression;
    @OneToMany(mappedBy = "utilisateur")
    private List<TentativeSupressionMdp> tentativeSuppressionMdp;
    @OneToMany(mappedBy = "utilisateur")
    private List<Invitation> invitation;
    @OneToMany(mappedBy = "utilisateur")
    private List<Reaction> reactions;
    @OneToMany(mappedBy = "admin")
    private List<Projet> projets;
    @OneToMany(mappedBy = "utilisateur")
    private List<Commentaire> commentaires;
    @OneToMany(mappedBy = "admin")
    private List<Equipe> equipes;


}
