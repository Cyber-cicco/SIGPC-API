package fr.diginamic.services;
import fr.diginamic.dto.EquipeTransformer;
import fr.diginamic.entities.Equipe;
import fr.diginamic.repository.UtilisateurRepository;
import fr.diginamic.shared.AuthenticationInfos;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.EquipeDto;
import fr.diginamic.repository.EquipeRepository;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Service
@RequiredArgsConstructor
@Validated
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final EquipeTransformer equipeTransformer;
    private final UtilisateurRepository utilisateurRepository;

    public Equipe postNewEquipe(AuthenticationInfos userInfos, @Valid EquipeDto equipeDto) {
        var utilisateur = utilisateurRepository.findById(userInfos.getId())
                .orElseThrow(EntityNotFoundException::new);
        var equipe = equipeTransformer.toequipe(utilisateur, equipeDto);
        return equipeRepository.save(equipe);
    }
}
