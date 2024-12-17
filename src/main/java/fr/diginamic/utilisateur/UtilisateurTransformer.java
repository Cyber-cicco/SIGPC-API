package fr.diginamic.utilisateur;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import fr.diginamic.entities.Utilisateur;

import java.util.UUID;

@Component
public class UtilisateurTransformer {

    public Utilisateur fromCompteToUtilisateur(CompteDto compteDto, String password, String roles, UUID activationLink) {
        return Utilisateur.builder()
                .nom(compteDto.getNom())
                .prenom(compteDto.getPrenom())
                .password(password)
                .activationLink(activationLink)
                .roles(roles)
                .email(compteDto.getEmail())
                .emailVerified(false)
                .build();
    }
    public UtilisateurDto toUtilisateurDto(Utilisateur entity){
        UtilisateurDto dto = new UtilisateurDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        if (entity.getActivationLink() != null){
            dto.setActivationLink(entity.getActivationLink().toString());
        }
        dto.setEmailVerified(entity.isEmailVerified());
        dto.setRoles(entity.getRoles());
        dto.setDateEcheanceSuppression(entity.getDateEcheanceSuppression());

        return dto;
    }      
}
