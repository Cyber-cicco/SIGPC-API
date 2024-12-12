package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Ressource {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private String nom;
    private String lien;
    private String lienMedia;
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;


}
