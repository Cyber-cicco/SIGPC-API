package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.UserStory;
import fr.diginamic.dto.UserStoryDto;

@Component
public class UserStoryTransformer {

    public UserStoryDto touserStoryDto(UserStory entity){
        UserStoryDto dto = new UserStoryDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());
        dto.setDateDebut(entity.getDateDebut());
        dto.setAvancement(entity.getAvancement());
        dto.setDateFin(entity.getDateFin());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public UserStory touserStory(UserStoryDto dto){
        UserStory entity = new UserStory();
        entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());
        entity.setDateDebut(dto.getDateDebut());
        entity.setAvancement(dto.getAvancement());
        entity.setDateFin(dto.getDateFin());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
