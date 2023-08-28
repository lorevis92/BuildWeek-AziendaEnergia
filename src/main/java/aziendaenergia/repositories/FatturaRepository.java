package aziendaenergia.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aziendaenergia.entities.Fattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

}
