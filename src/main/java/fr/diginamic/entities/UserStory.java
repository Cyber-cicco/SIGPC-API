package fr.diginamic.entities;

import fr.diginamic.entities.enums.AvancementEnum;
import fr.diginamic.projet.Projet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle", nullable = false, length = 120)
    private String libelle;

    @Column(name = "code")
    private String code;

    @Column(name = "description", nullable = true, length = 2048)
    private String description;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Enumerated(EnumType.STRING)
    @Column(name = "avancement", nullable = true)
    private AvancementEnum avancement;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "fin_estime")
    private LocalDate finEstime;

    @OneToMany(mappedBy = "userStory")
    private List<Tache> taches;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @OneToMany(mappedBy = "userStory")
    private List<Bug> bugs;

    @OneToMany(mappedBy = "userStory")
    private List<Document> documents;

    @ManyToOne
    @JoinColumn(name = "utilisateurAssigne_id")
    private Utilisateur utilisateurAssigne;

}
