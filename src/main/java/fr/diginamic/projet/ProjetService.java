package fr.diginamic.projet;

import fr.diginamic.exception.ResourceNotFoundException;
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

    ProjetDto projetDtoDansDb = modelMapper.map(projetInDb, ProjetDto.class);
    log.info("savedProjet: {}", projetDtoDansDb.toString());

    Projet update = modelMapper.map(updateDto, Projet.class);
    projetInDb.setNom(updateDto.getNom());
    projetInDb.setDescription(updateDto.getDescription());
    projetInDb.setDateDebut(updateDto.getDateDebut());
    projetInDb.setDateFin(updateDto.getDateFin());
    projetInDb.setDateFinEstimee(updateDto.getDateFinEstimee());
    projetInDb.setContact(updateDto.getContact());

    projetInDb = projetRepository.save(projetInDb);
    return modelMapper.map(projetInDb, ProjetDto.class);
  }

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
