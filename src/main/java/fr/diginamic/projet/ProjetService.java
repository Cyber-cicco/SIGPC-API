package fr.diginamic.projet;

import fr.diginamic.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    //    Projet savedProjet = projetTransformer.toProjet(projetDto);
    //    Projet savedProjet = projetRepository.findByNom(projet.getNom());
    //    if (savedProjet != null) {
    //      throw new ApiException("Un projet avec le nom '" + projetDto.getNom() + "' existe
    // déjà");
    //    }
    Projet savedProjet = projetRepository.save(projet);
    return modelMapper.map(savedProjet, ProjetDto.class);
    //    return projetTransformer.toProjetDto(savedProjet);
  }
}
