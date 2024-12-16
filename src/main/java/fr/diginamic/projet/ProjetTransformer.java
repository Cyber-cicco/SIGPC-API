package fr.diginamic.projet;

import org.springframework.stereotype.Component;

@Component
public class ProjetTransformer {

  public ProjetDto toProjetDto(Projet entity) {
    ProjetDto dto = new ProjetDto();
    //    dto.setId(entity.getId());
    //    dto.setNom(entity.getNom());
    //    dto.setDescription(entity.getDescription());
    //    dto.setDateDebut(entity.getDateDebut());
    //    dto.setDateFin(entity.getDateFin());
    //    dto.setDateFinEstimee(entity.getDateFinEstimee());
    //    dto.setContact(entity.getContact());

    // TODO : implémenter les méthodes pour les champs complexes
    return dto;
  }

  public Projet toProjet(ProjetDto dto) {
    Projet entity = new Projet();
    //    entity.setId(dto.getId());
    //    entity.setNom(dto.getNom());
    //    entity.setDescription(dto.getDescription());
    //    entity.setDateDebut(dto.getDateDebut());
    //    entity.setDateFin(dto.getDateFin());
    //    entity.setDateFinEstimee(dto.getDateFinEstimee());
    //    entity.setContact(dto.getContact());

    // TODO : implémenter les méthodes pour les champs complexes
    return entity;
  }

  // TODO : factorisation à envisager
  //  public ProjetDto toProjetDto2(Projet entity) {
  //    ProjetDto dto = new ProjetDto();
  //    mapCommonFields(entity, dto);
  //    // TODO : Implement methods for complex fields
  //    return dto;
  //  }
  //
  //  private void mapCommonFields(Projet entity, ProjetDto dto) {
  //    dto.setId(entity.getId());
  //    dto.setNom(entity.getNom());
  //    dto.setDateDebut(entity.getDateDebut());
  //    dto.setDateFin(entity.getDateFin());
  //    dto.setDateFinEstimee(entity.getDateFinEstimee());
  //    dto.setContact(entity.getContact());
  //  }
}
