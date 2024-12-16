package fr.diginamic.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjetRoleEnum {
    MEMBRE("MEMBRE"), SCRUM_MASTER("SCRUM_MASTER"), PRODUCT_OWNER("PRODUCT_OWNER"), VISITEUR("VISITEUR");

    private String role;
}
