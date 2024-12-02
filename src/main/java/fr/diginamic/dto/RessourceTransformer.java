package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.Ressource;
import fr.diginamic.dto.RessourceDto;

@Component
public class RessourceTransformer {

    public RessourceDto toressourceDto(Ressource entity){
        RessourceDto dto = new RessourceDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setLien(entity.getLien());
        dto.setLienMedia(entity.getLienMedia());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public Ressource toressource(RessourceDto dto){
        Ressource entity = new Ressource();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setLien(dto.getLien());
        entity.setLienMedia(dto.getLienMedia());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
