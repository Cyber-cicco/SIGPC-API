package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Sprint;

public interface SprintRepository extends JpaRepository<Sprint, Long>  {

}
