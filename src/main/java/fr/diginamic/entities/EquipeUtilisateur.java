package fr.diginamic.entities;

import fr.diginamic.entities.enums.EquipeRoleEnum;
import jakarta.persistence.*;
import lombok.*;

/**
 * TODO : Ajouter une clé composée
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EquipeUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    /**
     * TODO:  Changer l'enum par une chaine de caractère
     */
    @Enumerated
    private EquipeRoleEnum role;
}
