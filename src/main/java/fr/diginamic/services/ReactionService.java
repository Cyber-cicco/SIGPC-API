package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.ReactionDto;
import fr.diginamic.dto.ReactionTransformer;
import fr.diginamic.repository.ReactionRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionRepository reactionRepository;
    private final ReactionTransformer reactionTransformer;

}
