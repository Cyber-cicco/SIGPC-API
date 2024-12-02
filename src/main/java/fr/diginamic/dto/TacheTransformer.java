package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.Tache;
import fr.diginamic.dto.TacheDto;

@Component
public class TacheTransformer {

    public TacheDto totacheDto(Tache entity){
        TacheDto dto = new TacheDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setDescription(entity.getDescription());
        dto.setDone(entity.isDone());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public Tache totache(TacheDto dto){
        Tache entity = new Tache();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setDescription(dto.getDescription());
        entity.setDone(dto.isDone());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
