package fr.diginamic.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static fr.diginamic.config.Constants.API_VERSION_1;

@RestController
@RequestMapping(API_VERSION_1)
public class GeneralController {

  @GetMapping("/health")
  @ResponseStatus(HttpStatus.OK)
  public String apiHealth() {
    return "L'API est fonctionnelle";
  }
}
