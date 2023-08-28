package aziendaenergia.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aziendaenergia.entities.Comune;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, UUID> {
}
