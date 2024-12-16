package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.diginamic.dto.CommentaireDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.CommentaireService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commentaire")
public class CommentaireController {

    private final CommentaireService commentaireService;

    /**
     * Ajoute un commentaire
     * 
     * Information à mettre dans le body :
     * - contenu
     * - projetId
     * - responseId (optionnel)
     * 
     * @param commentaireDto
     * @return
     */
    @PostMapping("/ajouter")
    public ResponseEntity<CommentaireDto> ajouterCommentaire(@RequestBody CommentaireDto commentaireDto) {
        if (commentaireDto.getContenu() == null) {
            throw new IllegalArgumentException("Le contenu est obligatoire");
        }
        if (commentaireDto.getContenu().length() > 512) {
            throw new IllegalArgumentException("Votre commentaire est trop long");
        }
        if (commentaireDto.getProjet() == null) {
            throw new IllegalArgumentException("Le projet est obligatoire");
        }
        CommentaireDto nouveauCommentaire = commentaireService.ajouterCommentaire(commentaireDto);
        return ResponseEntity.ok(nouveauCommentaire);
    }

}
