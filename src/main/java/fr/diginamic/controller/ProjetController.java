package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.ProjetDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.ProjetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/projet")
public class ProjetController {

   
    private final ProjetService projetService;

}
