package aziendaenergia.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aziendaenergia.entities.Indirizzo;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, UUID> {
}
