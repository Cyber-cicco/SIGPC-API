package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;    
import java.time.LocalDateTime;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Evenement {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private LocalDateTime date;
    private String nom;
    private String type;
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;
    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;


}
