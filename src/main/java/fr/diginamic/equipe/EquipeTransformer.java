package fr.diginamic.equipe;


import fr.diginamic.entities.Utilisateur;
import org.springframework.stereotype.Component;
import fr.diginamic.entities.Equipe;

@Component
public class EquipeTransformer {

    public EquipeDto toequipeDto(Equipe entity){
        EquipeDto dto = new EquipeDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setContact(entity.getContact());
        dto.setDescription(entity.getDescription());

        return dto;
    }      

    public Equipe toequipe(Utilisateur utilisateur, EquipeDto dto){
        Equipe entity = new Equipe();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setAdmin(utilisateur);
        entity.setContact(dto.getContact());
        entity.setDescription(dto.getDescription());

        return entity;
    }      
}
