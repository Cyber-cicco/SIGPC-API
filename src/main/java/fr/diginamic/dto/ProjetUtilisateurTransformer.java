package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.ProjetUtilisateur;
import fr.diginamic.dto.ProjetUtilisateurDto;

@Component
public class ProjetUtilisateurTransformer {

    public ProjetUtilisateurDto toprojetUtilisateurDto(ProjetUtilisateur entity){
        ProjetUtilisateurDto dto = new ProjetUtilisateurDto();
        dto.setId(entity.getId());
        dto.setRole(entity.getRole());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public ProjetUtilisateur toprojetUtilisateur(ProjetUtilisateurDto dto){
        ProjetUtilisateur entity = new ProjetUtilisateur();
        entity.setId(dto.getId());
        entity.setRole(dto.getRole());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
