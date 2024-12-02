package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.Reaction;
import fr.diginamic.dto.ReactionDto;

@Component
public class ReactionTransformer {

    public ReactionDto toreactionDto(Reaction entity){
        ReactionDto dto = new ReactionDto();
        dto.setId(entity.getId());
        dto.setEmoji(entity.getEmoji());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public Reaction toreaction(ReactionDto dto){
        Reaction entity = new Reaction();
        entity.setId(dto.getId());
        entity.setEmoji(dto.getEmoji());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
