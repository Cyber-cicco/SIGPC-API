package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.Evenement;
import fr.diginamic.dto.EvenementDto;

@Component
public class EvenementTransformer {

    public EvenementDto toevenementDto(Evenement entity){
        EvenementDto dto = new EvenementDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setNom(entity.getNom());
        dto.setType(entity.getType());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public Evenement toevenement(EvenementDto dto){
        Evenement entity = new Evenement();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setNom(dto.getNom());
        entity.setType(dto.getType());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
