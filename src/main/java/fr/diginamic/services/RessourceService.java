package fr.diginamic.services;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.diginamic.dto.RessourceDto;
import fr.diginamic.entities.Ressource;
import fr.diginamic.projet.Projet;
import fr.diginamic.projet.ProjetRepository;
import fr.diginamic.repository.RessourceRepository;

@Service
@RequiredArgsConstructor
public class RessourceService {

    private final RessourceRepository ressourceRepository;
    private final ProjetRepository projetRepository;

    public RessourceDto ajoutRessource(RessourceDto ressourceDto) {
        // Récupérer le projet à partir de la base de données
        Projet projet = projetRepository.findById(ressourceDto.getProjetId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Projet non trouvé"));

        // Créer l'entité Ressource
        Ressource ressource = new Ressource();
        ressource.setNom(ressourceDto.getNom());
        ressource.setLien(ressourceDto.getLien());
        ressource.setLienMedia(ressourceDto.getLienMedia());
        ressource.setProjet(projet); // Associer le projet à la ressource

        // Sauvegarder la ressource
        Ressource savedRessource = ressourceRepository.save(ressource);
        return new RessourceDto(savedRessource.getId(), savedRessource.getNom(), savedRessource.getLien(),
                savedRessource.getLienMedia(), savedRessource.getProjet().getId());
    }

}
