package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.CommentaireDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.CommentaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/commentaire")
public class CommentaireController {

   
    private final CommentaireService commentaireService;

}
