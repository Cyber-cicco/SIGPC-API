package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long>  {

}
