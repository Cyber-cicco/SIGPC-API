package fr.diginamic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.EquipeDto;
import fr.diginamic.dto.EquipeTransformer;
import fr.diginamic.entities.Equipe;
import fr.diginamic.repository.EquipeRepository;

@Service
@RequiredArgsConstructor
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final EquipeTransformer equipeTransformer;

    public EquipeDto ajouterEquipe(EquipeDto equipeDto) {
        Equipe equipe = equipeTransformer.toequipe(equipeDto);
        equipe = equipeRepository.save(equipe);
        return equipeTransformer.toequipeDto(equipe);
    }

}
