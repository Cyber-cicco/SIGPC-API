package fr.diginamic.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 60)
    private String nom;

    @Column(name = "description", nullable = true)
    private String description;

    // Par défaut, la tâche n'est pas terminée
    @Column(name = "done", nullable = false, columnDefinition = "boolean default false")
    private boolean done;

    @Column(name = "date_debut", nullable = false)
    private Date dateDebut;

    @Column(name = "date_fin", nullable = true)
    private Date dateFin;

    @Column(name = "fin_estime", nullable = true)
    private Date finEstime;

    @ManyToOne
    @JoinColumn(name = "userStory_id")
    private UserStory userStory;

}
