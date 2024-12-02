package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.EquipeDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.EquipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/equipe")
public class EquipeController {

   
    private final EquipeService equipeService;

}
