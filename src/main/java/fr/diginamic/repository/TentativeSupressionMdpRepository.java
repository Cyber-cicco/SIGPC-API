package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import fr.diginamic.entities.TentativeSupressionMdp;

public interface TentativeSupressionMdpRepository extends JpaRepository<TentativeSupressionMdp, Long>  {
    Optional<TentativeSupressionMdp> findByLinkAndActiveUntilAfter(UUID link, LocalDateTime now);
}
