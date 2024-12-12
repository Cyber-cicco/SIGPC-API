package fr.diginamic.entities;

import fr.diginamic.entities.enums.AvancementEnum;
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

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private String libelle;
    private String code;
    private String description;
    private LocalDate dateDebut;
    @Enumerated
    private AvancementEnum avancement;
    private LocalDate dateFin;
    @OneToMany(mappedBy = "userStory")
    private List<Bug> bugs;
    @OneToMany(mappedBy = "userStory")
    private List<Document> documents;
    @ManyToOne
    @JoinColumn(name = "utilisateurAssigne_id")
    private Utilisateur utilisateurAssigne;


}
