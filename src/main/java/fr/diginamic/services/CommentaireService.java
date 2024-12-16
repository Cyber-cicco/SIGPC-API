package fr.diginamic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.CommentaireDto;
import fr.diginamic.dto.CommentaireTransformer;
import fr.diginamic.entities.Commentaire;
import fr.diginamic.entities.Projet;
import fr.diginamic.repository.CommentaireRepository;
import fr.diginamic.repository.ProjetRepository;

@Service
@RequiredArgsConstructor
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;
    private final CommentaireTransformer commentaireTransformer;
    private final ProjetRepository projetRepository;

    public CommentaireDto ajouterCommentaire(CommentaireDto commentaireDto) {
        Projet projet = projetRepository.findById(commentaireDto.getProjet().getId())
                .orElseThrow(() -> new IllegalArgumentException("Projet non trouvé"));

        Commentaire commentaire = commentaireTransformer.tocommentaire(commentaireDto);
        commentaire.setProjet(projet);
        commentaire = commentaireRepository.save(commentaire);
        return commentaireTransformer.tocommentaireDto(commentaire);
    }

}
