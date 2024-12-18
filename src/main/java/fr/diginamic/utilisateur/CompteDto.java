package fr.diginamic.utilisateur;

import fr.diginamic.validation.annotations.FieldsMustMatch;
import fr.diginamic.validation.annotations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldsMustMatch(
        firstField = "password",
        secondField = "passwordConf",
        message = "Le mot de passe et sa confirmation doivent être identiques"
)
public class CompteDto {

    @Email
    @Size(max = 255)
    @Size(min = 8)
    @NotNull
    private String email;
    @Size(max = 60)
    @NotBlank
    private String prenom;
    @Size(max = 60)
    @NotBlank
    private String nom;
    @Size(min = 8)
    @Size(max = 40)
    @NotNull
    @ValidPassword
    private String password;
    @NotNull
    private String passwordConf;
}
