package fr.diginamic.entities;

import fr.diginamic.entities.enums.AvancementEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;    
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
