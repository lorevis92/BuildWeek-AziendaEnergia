package aziendaenergia.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import aziendaenergia.entities.Comune;
import aziendaenergia.exceptions.NotFoundException;
import aziendaenergia.repositories.ComuneRepository;

@Service
public class ComuniService {

	@Autowired
	ComuneRepository comuneRepository;

	public Comune saveComune(Comune comune) {
		return comuneRepository.save(comune);
	}

	public Page<Comune> findComuniandPage(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return comuneRepository.findAll(pageable);
	}

	public List<Comune> findComuni() {
		return comuneRepository.findAll();
	}

	public Comune findByDenominazione(String denominazione) {
		return comuneRepository.findByDenominazione(denominazione).orElseThrow(
				() -> new NotFoundException("Nessun comune corrispondente a: " + denominazione + " è stato trovato."));
	}

	public Comune findById(UUID id) {
		return comuneRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Nessun comune con ID: " + id + " è stato trovato."));
	}

	public void deleteComune(String denominazione) {
		Comune comune = findByDenominazione(denominazione);
		comuneRepository.delete(comune);
	}

}
