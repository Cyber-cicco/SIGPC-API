package fr.diginamic.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class TentativeChangementMdp {

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
