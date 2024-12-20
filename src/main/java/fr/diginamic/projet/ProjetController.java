package fr.diginamic.projet;

import static fr.diginamic.config.Constants.API_VERSION_1;
import static fr.diginamic.config.Constants.AUTH_TOKEN;
import static fr.diginamic.shared.ResponseUtil.success;

import fr.diginamic.services.SecurityService;
import fr.diginamic.shared.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(API_VERSION_1 + "/projets")
public class ProjetController {

  private final ProjetService projetService;
  private final SecurityService securityService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ApiResponse<List<ProjetDto>>> getAllProjets() {
    List<ProjetDto> listeProjets = projetService.getAllProjets();
    ApiResponse<List<ProjetDto>> apiResponse = success("Liste des projets", listeProjets);
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping("/{idProjet}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ApiResponse<ProjetDto>> getProjetById(@PathVariable Long idProjet) {
    ProjetDto projetDto = projetService.getProjetById(idProjet);
    ApiResponse<ProjetDto> apiResponse = success("Projet trouvé", projetDto);
    return ResponseEntity.ok().body(apiResponse);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ApiResponse<ProjetDto>> createProjet(
          @CookieValue(AUTH_TOKEN) String token,
          @Valid @RequestBody ProjetDto projetDto) {

    var userInfos = securityService.getAuthenticationInfos(token);
    ProjetDto createdProjetDto = projetService.createProjet(userInfos.getId(), projetDto);
    ApiResponse<ProjetDto> apiResponse = success("Projet créé", createdProjetDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }

  @PutMapping("/{idProjet}")
  public ResponseEntity<ApiResponse<ProjetDto>> updateProjet(
      @PathVariable Long idProjet, @Valid @RequestBody ProjetDto projetUpdateDto) {
    ProjetDto updatedProjetDto = projetService.updateProjet(idProjet, projetUpdateDto);
    ApiResponse<ProjetDto> apiResponse = success("Projet mis à jour avec succès", updatedProjetDto);
    return ResponseEntity.ok().body(apiResponse);
  }

  @DeleteMapping("/{idProjet}")
  public ResponseEntity<ApiResponse<ProjetDto>> deleteProjet(@PathVariable Long idProjet) {
    ProjetDto projetInDb = projetService.deleteProjet(idProjet);
    ApiResponse<ProjetDto> apiResponse =
        success("Projet avec id:" + idProjet + " supprimé", projetInDb);
    return ResponseEntity.ok().body(apiResponse);
  }
}
