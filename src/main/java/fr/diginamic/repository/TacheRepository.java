package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Tache;

public interface TacheRepository extends JpaRepository<Tache, Long>  {

}
