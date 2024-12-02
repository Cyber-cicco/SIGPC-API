package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.Commentaire;
import fr.diginamic.dto.CommentaireDto;

@Component
public class CommentaireTransformer {

    public CommentaireDto tocommentaireDto(Commentaire entity){
        CommentaireDto dto = new CommentaireDto();
        dto.setId(entity.getId());
        dto.setContenu(entity.getContenu());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public Commentaire tocommentaire(CommentaireDto dto){
        Commentaire entity = new Commentaire();
        entity.setId(dto.getId());
        entity.setContenu(dto.getContenu());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
