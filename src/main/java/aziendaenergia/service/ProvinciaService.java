package aziendaenergia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import aziendaenergia.entities.Provincia;
import aziendaenergia.exceptions.NotFoundException;
import aziendaenergia.repositories.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepository;

	public Provincia saveProvincia(Provincia provincia) {
		return provinciaRepository.save(provincia);
	}

	public Page<Provincia> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return provinciaRepository.findAll(pageable);
	}

	public List<Provincia> findProvince() {
		return provinciaRepository.findAll();
	}

	public Provincia findByNome(String nome) {
		return provinciaRepository.findByNome(nome).orElseThrow(
				() -> new NotFoundException("Nessun comune corrispondente a: " + nome + " è stato trovato."));
	}

	public Provincia findBySigla(String sigla) {
		return provinciaRepository.findBySigla(sigla).orElseThrow(
				() -> new NotFoundException("Nessun comune corrispondente a: " + sigla + " è stato trovato."));
	}

	public void deleteProvincia(String nome_provincia) {
		Provincia provincia = findByNome(nome_provincia);
		provinciaRepository.delete(provincia);
	}

}
