package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.Projet;
import fr.diginamic.dto.ProjetDto;

@Component
public class ProjetTransformer {

    public ProjetDto toprojetDto(Projet entity){
        ProjetDto dto = new ProjetDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setFinEstimee(entity.getFinEstimee());
        dto.setContact(entity.getContact());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public Projet toprojet(ProjetDto dto){
        Projet entity = new Projet();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setFinEstimee(dto.getFinEstimee());
        entity.setContact(dto.getContact());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
