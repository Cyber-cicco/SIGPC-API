package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.TentativeChangementMdp;

@Component
public class TentativeSupressionMdpTransformer {

    public TentativeSupressionMdpDto totentativeSupressionMdpDto(TentativeChangementMdp entity){
        TentativeSupressionMdpDto dto = new TentativeSupressionMdpDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setLink(entity.getLink());
        dto.setActiveUntil(entity.getActiveUntil());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public TentativeChangementMdp totentativeSupressionMdp(TentativeSupressionMdpDto dto){
        TentativeChangementMdp entity = new TentativeChangementMdp();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setLink(dto.getLink());
        entity.setActiveUntil(dto.getActiveUntil());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
