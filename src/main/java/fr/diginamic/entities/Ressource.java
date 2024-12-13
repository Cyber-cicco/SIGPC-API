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

    @Column(nullable = true)
    private String nom;

    @Column(nullable = true)
    private String lien;

    @Column(nullable = true)
    private String lienMedia;

    @ManyToOne
    @JoinColumn(name = "projet_id", nullable = false)
    private Projet projet;

}
