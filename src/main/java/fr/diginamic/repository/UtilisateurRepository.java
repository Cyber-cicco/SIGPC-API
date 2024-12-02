package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>  {

}
