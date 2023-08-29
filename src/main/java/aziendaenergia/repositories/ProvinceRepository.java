package aziendaenergia.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aziendaenergia.entities.Provincia;

@Repository
public interface ProvinceRepository extends JpaRepository<Provincia, UUID> {

}
