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

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private String nom;
    private String description;
    private boolean done;
    @ManyToOne
    @JoinColumn(name = "userStory_id")
    private UserStory userStory;


}
