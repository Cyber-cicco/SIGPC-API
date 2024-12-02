package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long>  {

}
