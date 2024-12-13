package fr.diginamic.entities;

import fr.diginamic.dto.EquipeDto;
import fr.diginamic.entities.enums.EquipeRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @Enumerated
    private EquipeRoleEnum role;
}
