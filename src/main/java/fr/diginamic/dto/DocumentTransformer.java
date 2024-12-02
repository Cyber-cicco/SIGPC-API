package fr.diginamic.dto;


import org.springframework.stereotype.Component;
import fr.diginamic.entities.Document;
import fr.diginamic.dto.DocumentDto;

@Component
public class DocumentTransformer {

    public DocumentDto todocumentDto(Document entity){
        DocumentDto dto = new DocumentDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setExtension(entity.getExtension());

        //TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }      

    public Document todocument(DocumentDto dto){
        Document entity = new Document();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setExtension(dto.getExtension());

        //TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }      
}
