package aziendaenergia.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aziendaenergia.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	Optional<Cliente> findByEmail(String email);
}
