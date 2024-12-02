package fr.diginamic.dto;

import fr.diginamic.entities.enums.TypeInvitationEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;
import fr.diginamic.dto.UtilisateurDto;
import fr.diginamic.dto.EquipeDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InvitationDto {

    private Long id;
    private LocalDateTime dateEmission;
    private boolean acceptee;
    private LocalDateTime dateAcceptation;
    private TypeInvitationEnum typeInvitation;
    private UtilisateurDto utilisateur;
    private EquipeDto equipe;

}
