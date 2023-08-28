package aziendaenergia.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import aziendaenergia.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	Optional<Cliente> findByEmail(String email);

	@Query("SELECT c FROM Cliente c "
			+ "WHERE (:minFatturatoAnnuale IS NULL OR c.fatturatoAnnuale >= :minFatturatoAnnuale) "
			+ "AND (:dataInserimento IS NULL OR c.dataInserimento >= :dataInserimento) "
			+ "AND (:dataUltimoContatto IS NULL OR c.dataUltimoContatto >= :dataUltimoContatto) "
			+ "AND (:parteNome IS NULL OR LOWER(c.nomeContatto) LIKE %:parteNome%)")
	Page<Cliente> filtraClienti(Double minFatturatoAnnuale, LocalDate dataInserimento, LocalDate dataUltimoContatto,
			String parteNome, Pageable pageable);
}
