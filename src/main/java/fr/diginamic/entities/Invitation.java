package fr.diginamic.entities;

import fr.diginamic.entities.enums.TypeInvitationEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Invitation {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private LocalDateTime dateEmission;
    private boolean acceptee;
    private LocalDateTime dateAcceptation;
    @Enumerated
    private TypeInvitationEnum typeInvitation;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;


}
