package fr.diginamic.projet;

import fr.diginamic.entities.ProjetUtilisateur;
import fr.diginamic.entities.enums.ProjetRoleEnum;
import fr.diginamic.equipe.EquipeRepository;
import fr.diginamic.exception.ResourceNotFoundException;
import fr.diginamic.utilisateur.UtilisateurRepository;
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
  private final UtilisateurRepository utilisateurRepository;
  private final ProjetUtilisateurRepository projetUtilisateurRepository;
  private final EquipeRepository equipeRepository;
  private final ModelMapper modelMapper;

  public List<ProjetDto> getAllProjets() {
    List<Projet> projetsDansDb = projetRepository.findAll();
    List<ProjetDto> projetsDtos;
    projetsDtos = projetsDansDb.stream().map(projet -> modelMapper.map(projet, ProjetDto.class)).toList();
    return projetsDtos;
  }

  public ProjetDto getProjetById(Long projetId) {
    Projet projetDansDb = projetRepository
        .findById(projetId)
        .orElseThrow(() -> new ResourceNotFoundException("Projet", "id", projetId));
    return modelMapper.map(projetDansDb, ProjetDto.class);
  }

  public ProjetDto createProjet(Long userId, ProjetDto projetDto) {
    var projet = modelMapper.map(projetDto, Projet.class);
    var utilisateur = utilisateurRepository.getReferenceById(userId);
    if (projetDto.getEquipeId() != null) {
      var equipe = equipeRepository.getReferenceById(projetDto.getEquipeId());
      projet.setEquipe(equipe);
    }
    projet.setAdmin(utilisateur);
    
    projetUtilisateurRepository.save(
        ProjetUtilisateur.builder()
            .projet(projet)
            .utilisateur(utilisateur)
            .role(ProjetRoleEnum.MEMBRE)
            .build());

    return modelMapper.map(projetRepository.save(projet), ProjetDto.class);
  }

  public ProjetDto updateProjet(Long projetId, ProjetDto updateDto) {
    Projet projetInDb = projetRepository
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
    Projet projetInDb = projetRepository
        .findById(projetId)
        .orElseThrow(() -> new ResourceNotFoundException("Projet", "id", projetId));

    ProjetDto deleted = modelMapper.map(projetInDb, ProjetDto.class);
    projetRepository.delete(projetInDb);
    return deleted;
  }
}
