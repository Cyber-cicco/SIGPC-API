package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.SprintDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.SprintService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/sprint")
public class SprintController {

   
    private final SprintService sprintService;

}
