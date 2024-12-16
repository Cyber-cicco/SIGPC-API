package fr.diginamic.equipe;

import fr.diginamic.entities.enums.TypeInvitationEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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
    private Long utilisateurId;
    private Long equipeId;

}
