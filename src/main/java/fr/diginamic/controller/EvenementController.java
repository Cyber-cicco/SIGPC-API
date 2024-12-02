package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.EvenementDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.EvenementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/evenement")
public class EvenementController {

   
    private final EvenementService evenementService;

}
