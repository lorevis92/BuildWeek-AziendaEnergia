package aziendaenergia.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aziendaenergia.entities.Comune;
import aziendaenergia.service.ComuniService;

@RestController
@RequestMapping("/comuni")
public class ComuniController {

	@Autowired
	ComuniService comuneService;

	@GetMapping("")
	public Page<Comune> findComuni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return comuneService.findComuniandPage(page, size, sortBy);
	}

	@GetMapping("/{denominazione}")
	public Comune findByDenominazione(String denominazione) {
		return comuneService.findByDenominazione(denominazione);
	}

	@GetMapping("/{id}")
	public Comune findById(UUID id) {
		return comuneService.findById(id);
	}

	@PostMapping
	public Comune createComune(Comune comune) {
		return comuneService.saveComune(comune);
	}

}
