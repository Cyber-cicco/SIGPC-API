package fr.diginamic.projet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
  Boolean existsByNom(
      @NotBlank
          @Size(min = 5, message = "Le nom du projet doit " + "contenir au moins 5 caractères")
          String nom);
}
