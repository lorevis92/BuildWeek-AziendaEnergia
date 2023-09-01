package aziendaenergia.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

	Page<Fattura> findByClienteId(UUID id, Pageable pageble);

	@Query("SELECT c FROM Fattura c WHERE DATE(c.data) = DATE(:data)")
	Page<Fattura> findByData(@Param("data") LocalDate data, Pageable pageable);

	Page<Fattura> findByAnno(int anno, Pageable pageable);

	Page<Fattura> findByStato(Stato stato, Pageable pageable);

	Page<Fattura> findByImportoBetween(BigDecimal minImporto, BigDecimal maxImporto, Pageable pageable);
	
	List<Fattura> findByStato(Stato stato);
	
	
}
