package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.Invitation;
import fr.diginamic.dto.InvitationDto;

@Component
public class InvitationTransformer {

    public InvitationDto toinvitationDto(Invitation entity){
        InvitationDto dto = new InvitationDto();
        dto.setId(entity.getId());
        dto.setDateEmission(entity.getDateEmission());
        dto.setAcceptee(entity.isAcceptee());
        dto.setDateAcceptation(entity.getDateAcceptation());
        dto.setTypeInvitation(entity.getTypeInvitation());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public Invitation toinvitation(InvitationDto dto){
        Invitation entity = new Invitation();
        entity.setId(dto.getId());
        entity.setDateEmission(dto.getDateEmission());
        entity.setAcceptee(dto.isAcceptee());
        entity.setDateAcceptation(dto.getDateAcceptation());
        entity.setTypeInvitation(dto.getTypeInvitation());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
