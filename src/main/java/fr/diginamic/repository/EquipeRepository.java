package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long>  {

}
