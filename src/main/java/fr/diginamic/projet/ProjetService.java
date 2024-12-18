package fr.diginamic.projet;

import fr.diginamic.exception.ResourceNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjetService implements ProjetServiceInterface {

  private final ProjetRepository projetRepository;
  private final ModelMapper modelMapper;

  public List<ProjetDto> getAllProjets() {
    List<Projet> projetsDansDb = projetRepository.findAll();
    List<ProjetDto> projetsDtos;
    projetsDtos =
        projetsDansDb.stream().map(projet -> modelMapper.map(projet, ProjetDto.class)).toList();
    return projetsDtos;
  }

  public ProjetDto getProjetById(Long projetId) {
    Projet projetDansDb =
        projetRepository
            .findById(projetId)
            .orElseThrow(() -> new ResourceNotFoundException("Projet", "id", projetId));
    return modelMapper.map(projetDansDb, ProjetDto.class);
  }

  public ProjetDto createProjet(ProjetDto projetDto) {
    Projet projet = modelMapper.map(projetDto, Projet.class);
    Projet savedProjet = projetRepository.save(projet);
    return modelMapper.map(savedProjet, ProjetDto.class);
  }

  public ProjetDto updateProjet(Long projetId, ProjetDto updateDto) {
    Projet projetInDb =
        projetRepository
            .findById(projetId)
            .orElseThrow(() -> new ResourceNotFoundException("Projet", "id", projetId));

    //    ProjetDto projetInDb = getProjetById(projetId);

    ProjetDto projetDtoDansDb = modelMapper.map(projetInDb, ProjetDto.class);
    log.info("savedProjet: {}", projetDtoDansDb.toString());

    //    Map<String, Object> fields = new HashMap<>();
    Projet update = modelMapper.map(updateDto, Projet.class);
    projetInDb.setNom(updateDto.getNom());
    projetInDb.setDescription(updateDto.getDescription());
    projetInDb.setDateDebut(updateDto.getDateDebut());
    projetInDb.setDateFin(updateDto.getDateFin());
    projetInDb.setDateFinEstimee(updateDto.getDateFinEstimee());
    projetInDb.setContact(updateDto.getContact());

    for (Field field : Projet.class.getDeclaredFields()) {
      //      try {
      String fieldName = field.getName();
      log.info("fieldName: {}", fieldName);
      //        if (!fieldName.equals("id")) {
      //          field.setAccessible(true);
      //          //        var initialValue = field.get(projetDtoDansDb);
      //          var finalValue = field.get(updateDto);
      //          ReflectionUtils.setField(field, projetInDb, finalValue);
      //          //        Object value = field.get(projetDtoDansDb);
      //          //        fields.put(field.getName(), value);
      //        }
      //      } catch (IllegalAccessException e) {
      //        log.error("Erreur dans updateProjet in ProjetService");
      //        continue;
      //      }
    }

    //    Projet projetUpdate = modelMapper.map(updateDto, Projet.class);

    projetInDb = projetRepository.save(projetInDb);
    return modelMapper.map(projetInDb, ProjetDto.class);
  }

  //  public ProjetDto partialProjetUpdate(ProjetDto projetUpdateDto, Long projetId) {
  //    Projet savedProjet =
  //        projetRepository
  //            .findById(projetId)
  //            .orElseThrow(
  //                () -> new ResourceNotFoundException("Projet", "id", projetUpdateDto.getId()));
  //
  //    ProjetDto savedProjetDto = modelMapper.map(savedProjet, ProjetDto.class);
  //
  //    Projet projetUpdate = modelMapper.map(projetUpdateDto, Projet.class);
  //    savedProjet.setNom(projetUpdate.getNom());
  //  }

  public ProjetDto deleteProjet(Long projetId) {
    Projet projetInDb =
        projetRepository
            .findById(projetId)
            .orElseThrow(() -> new ResourceNotFoundException("Projet", "id", projetId));

    ProjetDto deleted = modelMapper.map(projetInDb, ProjetDto.class);
    projetRepository.delete(projetInDb);
    return deleted;
  }
}
