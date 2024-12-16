package fr.diginamic.utilisateur;

import fr.diginamic.validation.annotations.FieldsMustMatch;
import fr.diginamic.validation.annotations.ValidPassword;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldsMustMatch(
        firstField = "password",
        secondField = "passwordConf",
        message = "Le mot de passe et sa confirmation doivent être identiques"
)
public class PasswordChangeDto {
    @Size(min = 8)
    @Size(max = 40)
    @ValidPassword
    @NotNull
    private String password;
    @NotNull
    private String passwordConf;
}
