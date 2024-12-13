package fr.diginamic.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.diginamic.entities.enums.RoleEnum;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import fr.diginamic.entities.Utilisateur;

import java.util.List;
import java.util.UUID;

@Component
public class UtilisateurTransformer {

    public Utilisateur fromCompteToUtilisateur(CompteDto compteDto, String password, String roles, UUID activationLink) throws JsonProcessingException {
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
    public UtilisateurDto toutilisateurDto(Utilisateur entity){
        UtilisateurDto dto = new UtilisateurDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setEmailVerified(entity.isEmailVerified());
        dto.setRoles(entity.getRoles());
        dto.setDateEcheanceSuppression(entity.getDateEcheanceSuppression());

        return dto;
    }      

    public Utilisateur toutilisateur(UtilisateurDto dto){
        Utilisateur entity = new Utilisateur();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmailVerified(dto.isEmailVerified());
        entity.setRoles(dto.getRoles());
        entity.setDateEcheanceSuppression(dto.getDateEcheanceSuppression());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
