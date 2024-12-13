package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    @Column(length = 120)
    private String email;
    @Column(length = 60)
    private String nom;
    @Column(length = 60)
    private String prenom;
    @Column(length = 60)
    private String password;
    private boolean emailVerified;
    private UUID activationLink;
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
    @OneToMany(mappedBy = "utilisateur")
    private List<EquipeUtilisateur> equipeUtilisateurs;


}
