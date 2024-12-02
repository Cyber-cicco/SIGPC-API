package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;    
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Commentaire {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private String contenu;
    @ManyToOne
    @JoinColumn(name = "responseA_id")
    private Commentaire responseA;
    @OneToMany(mappedBy = "commentaire")
    private List<Commentaire> responses;
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "commentaire")
    private List<Reaction> reactions;


}
