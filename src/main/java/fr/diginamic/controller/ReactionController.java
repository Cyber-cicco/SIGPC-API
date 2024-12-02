package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.ReactionDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.ReactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reaction")
public class ReactionController {

   
    private final ReactionService reactionService;

}
