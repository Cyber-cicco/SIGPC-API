package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.CommentaireDto;
import fr.diginamic.dto.CommentaireTransformer;
import fr.diginamic.repository.CommentaireRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;
    private final CommentaireTransformer commentaireTransformer;

}
