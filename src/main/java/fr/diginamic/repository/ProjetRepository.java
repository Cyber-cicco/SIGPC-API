package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long>  {

}
