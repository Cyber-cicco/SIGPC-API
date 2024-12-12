package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Equipe {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private String nom;
    private String contact;
    private String description;
    @OneToMany(mappedBy = "equipe")
    private List<Projet> projets;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Utilisateur admin;
    @OneToMany(mappedBy = "equipe")
    private List<Invitation> invitations;


}
