package fr.diginamic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import fr.diginamic.dto.ProjetDto;
import java.util.List;
import fr.diginamic.dto.UtilisateurDto;
import fr.diginamic.dto.InvitationDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EquipeDto {

    private Long id;
    @NotBlank
    @Size(min = 3, max = 120)
    private String nom;
    @Email
    @NotBlank
    @Size(max = 255)
    private String contact;
    @NotBlank
    @Size(min = 5, max = 255)
    private String description;

}
