package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;    
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class TentativeSupressionMdp {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private LocalDateTime date;
    private UUID link;
    private LocalDateTime activeUntil;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;


}
