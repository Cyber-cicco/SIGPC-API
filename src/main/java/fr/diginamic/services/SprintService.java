package fr.diginamic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.SprintDto;
import fr.diginamic.dto.SprintTransformer;
import fr.diginamic.entities.Sprint;
import fr.diginamic.repository.SprintRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintService {

    private final SprintRepository sprintRepository;
    private final SprintTransformer sprintTransformer;

    public SprintDto creerSprint(SprintDto sprintDto) {
        // 1. Validation des informations nécessaires
        if (sprintDto.getDateDebut() == null || sprintDto.getDateFin() == null || sprintDto.getUserStories() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Veuillez fournir toutes les informations requises pour créer un sprint (nom, dates, objectifs).");
        }

        // 2. Vérification des chevauchements de dates
        List<Sprint> existingSprints = sprintRepository.findAll();
        for (Sprint existingSprint : existingSprints) {
            if (sprintDto.getDateDebut().isBefore(existingSprint.getDateFin())
                    && sprintDto.getDateFin().isAfter(existingSprint.getDateDebut())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Les dates du nouveau sprint chevauchent un sprint existant. Veuillez choisir des dates différentes.");
            }
        }

        // 3. Vérification de la capacité de l'équipe
        if (!isCapacitySufficient(sprintDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La capacité de l'équipe pour ce sprint a été dépassée. Veuillez réduire le nombre de tâches assignées.");
        }

        // 4. Vérification des objectifs clairs et mesurables
        if (sprintDto.getUserStories().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Chaque sprint doit avoir des objectifs clairs. Veuillez définir des objectifs avant de créer le sprint.");
        }

        Sprint sprint = sprintTransformer.tosprint(sprintDto);
        Sprint savedSprint = sprintRepository.save(sprint);
        return sprintTransformer.tosprintDto(savedSprint);
    }

    private boolean isCapacitySufficient(SprintDto sprintDto) {
        // Implémentez la logique pour vérifier la capacité de l'équipe
        return true;
    }
}
