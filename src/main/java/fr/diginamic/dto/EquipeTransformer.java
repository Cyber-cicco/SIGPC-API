package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.Equipe;
import fr.diginamic.dto.EquipeDto;

@Component
public class EquipeTransformer {

    public EquipeDto toequipeDto(Equipe entity){
        EquipeDto dto = new EquipeDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setContact(entity.getContact());
        dto.setDescription(entity.getDescription());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public Equipe toequipe(EquipeDto dto){
        Equipe entity = new Equipe();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setContact(dto.getContact());
        entity.setDescription(dto.getDescription());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
