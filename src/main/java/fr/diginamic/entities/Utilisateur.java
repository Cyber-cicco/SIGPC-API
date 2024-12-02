package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Utilisateur {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private String email;
    private String nom;
    private String prenom;
    private boolean email_verified;
    private String roles;
    private LocalDateTime dateEcheanceSuppression;
    @OneToMany(mappedBy = "utilisateur")
    private List<TentativeSupressionMdp> tentativeSuppressionMdp;
    @OneToMany(mappedBy = "utilisateur")
    private List<Invitation> invitation;
    @OneToMany(mappedBy = "utilisateur")
    private List<Reaction> reactions;
    @OneToMany(mappedBy = "utilisateur")
    private List<Projet> projets;
    @OneToMany(mappedBy = "utilisateur")
    private List<Utilisateur> commentaires;
    @OneToMany(mappedBy = "utilisateur")
    private List<Equipe> equipes;


}
