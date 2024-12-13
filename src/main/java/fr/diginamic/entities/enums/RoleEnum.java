package fr.diginamic.entities.enums;

public enum RoleEnum {
    USER("USER"), ADMIN("ADMIN");

    public final String role;

    RoleEnum(String role){
        this.role = role;
    }

}
