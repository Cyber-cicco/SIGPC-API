package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;    
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Sprint {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Integer sprintParJour;
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;
    @ManyToMany
    @JoinTable(name = "sprint_userStories",
            joinColumns = @JoinColumn(name = "sprint_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "userStories_id", referencedColumnName = "id")
    )
    private List<UserStory> userStories;


}
