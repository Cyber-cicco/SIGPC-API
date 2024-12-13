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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = true)
    private String contact;

    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "equipe")
    private List<Projet> projets;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Utilisateur admin;

    @OneToMany(mappedBy = "equipe")
    private List<Invitation> invitations;

}
