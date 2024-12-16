package fr.diginamic.dto;

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
public class PasswordChangeDto {
    @Size(min = 8)
    @Size(max = 40)
    @NotNull
    private String password;
    @NotNull
    private String passwordConf;
}
