package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Document {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private String nom;
    private String extension;
    @ManyToOne
    @JoinColumn(name = "userStory_id")
    private UserStory userStory;


}
