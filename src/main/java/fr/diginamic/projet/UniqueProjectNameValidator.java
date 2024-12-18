package fr.diginamic.projet;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueProjectNameValidator implements ConstraintValidator<UniqueProjectName, String> {

  private final ProjetRepository projetRepository;

  @Override
  public boolean isValid(String nomProjet, ConstraintValidatorContext constraintValidatorContext) {
    return !projetRepository.existsByNom(nomProjet);
  }
}
