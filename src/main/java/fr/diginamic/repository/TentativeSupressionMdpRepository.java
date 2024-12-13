package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import fr.diginamic.entities.TentativeChangementMdp;

public interface TentativeSupressionMdpRepository extends JpaRepository<TentativeChangementMdp, Long>  {
    Optional<TentativeChangementMdp> findByLinkAndActiveUntilAfter(UUID link, LocalDateTime now);
}
