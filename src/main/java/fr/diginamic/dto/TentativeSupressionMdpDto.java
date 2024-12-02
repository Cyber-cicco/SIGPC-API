package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TentativeSupressionMdpDto {

    private Long id;
    private LocalDateTime date;
    private UUID link;
    private LocalDateTime activeUntil;
    private UtilisateurDto utilisateur;

}
