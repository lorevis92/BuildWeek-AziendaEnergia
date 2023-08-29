package aziendaenergia.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aziendaenergia.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	Optional<Cliente> findByEmail(String email);

	Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(Double minFatturatoAnnuale, Pageable pageable);

	@Query("SELECT c FROM Cliente c WHERE DATE(c.dataInserimento) = DATE(:dataInserimento)")
	Page<Cliente> findByDataInserimento(@Param("dataInserimento") LocalDate dataInserimento, Pageable pageable);

	@Query("SELECT c FROM Cliente c WHERE DATE(c.dataUltimoContatto) = DATE(:dataUltimoContatto)")
	Page<Cliente> findByDataUltimoContatto(@Param("dataUltimoContatto") LocalDate dataUltimoContatto,
			Pageable pageable);

	Page<Cliente> findByNomeContattoContainingIgnoreCase(String parteNome, Pageable pageable);
}
