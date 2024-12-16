package fr.diginamic.projet;

import fr.diginamic.exception.ApiException;
import fr.diginamic.exception.ResourceNotFoundException;

import java.util.List;

public interface ProjetServiceInterface {
  List<ProjetDto> getAllProjets();

  ProjetDto getProjetById(Long idProjet) throws ResourceNotFoundException;

  ProjetDto createProjet(Long userId, ProjetDto projetDto) throws ApiException;
}
