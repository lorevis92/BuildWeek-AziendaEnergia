package aziendaenergia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aziendaenergia.entities.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, String> {

	Optional<Provincia> findByNome(String nome);

	Optional<Provincia> findBySigla(String sigla);
}
