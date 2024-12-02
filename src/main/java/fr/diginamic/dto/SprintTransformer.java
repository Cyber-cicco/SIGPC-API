package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.Sprint;
import fr.diginamic.dto.SprintDto;

@Component
public class SprintTransformer {

    public SprintDto tosprintDto(Sprint entity){
        SprintDto dto = new SprintDto();
        dto.setId(entity.getId());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setSprintParJour(entity.getSprintParJour());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public Sprint tosprint(SprintDto dto){
        Sprint entity = new Sprint();
        entity.setId(dto.getId());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setSprintParJour(dto.getSprintParJour());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
