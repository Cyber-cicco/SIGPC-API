package fr.diginamic.entities;

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

    @ManyToOne
    @JoinColumn(name = "userStory_id")
    private UserStory userStory;

}
