package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.TacheDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.TacheService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/tache")
public class TacheController {

   
    private final TacheService tacheService;

}
