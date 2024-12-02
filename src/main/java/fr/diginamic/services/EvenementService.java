package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.EvenementDto;
import fr.diginamic.dto.EvenementTransformer;
import fr.diginamic.repository.EvenementRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EvenementService {

    private final EvenementRepository evenementRepository;
    private final EvenementTransformer evenementTransformer;

}
