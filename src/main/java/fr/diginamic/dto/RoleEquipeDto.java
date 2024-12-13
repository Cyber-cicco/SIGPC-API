package fr.diginamic.dto;

import fr.diginamic.entities.enums.EquipeRoleEnum;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleEquipeDto {
    private EquipeRoleEnum role;
}
