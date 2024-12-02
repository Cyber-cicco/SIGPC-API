package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;    

@NoArgsConstructor
@AllArgsConstructor
@Data
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
