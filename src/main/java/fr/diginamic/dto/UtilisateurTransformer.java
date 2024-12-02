package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.Utilisateur;
import fr.diginamic.dto.UtilisateurDto;

@Component
public class UtilisateurTransformer {

    public UtilisateurDto toutilisateurDto(Utilisateur entity){
        UtilisateurDto dto = new UtilisateurDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setEmail_verified(entity.isEmail_verified());
        dto.setRoles(entity.getRoles());
        dto.setDateEcheanceSuppression(entity.getDateEcheanceSuppression());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public Utilisateur toutilisateur(UtilisateurDto dto){
        Utilisateur entity = new Utilisateur();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail_verified(dto.isEmail_verified());
        entity.setRoles(dto.getRoles());
        entity.setDateEcheanceSuppression(dto.getDateEcheanceSuppression());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
