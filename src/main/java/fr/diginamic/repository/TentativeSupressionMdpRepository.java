package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.TentativeSupressionMdp;

public interface TentativeSupressionMdpRepository extends JpaRepository<TentativeSupressionMdp, Long>  {

}
