package aziendaenergia.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;
import aziendaenergia.payload.NewFatturaPayload;
import aziendaenergia.service.FatturaService;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

	@Autowired
	FatturaService fatturaService;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Fattura saveUser(@RequestBody NewFatturaPayload body) {
		Fattura createdFattura = fatturaService.save(body);
		return createdFattura;
	}

	@GetMapping("")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public Page<Fattura> getFatture(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return fatturaService.find(page, size, sortBy);
	}

	@GetMapping("/{fatturaId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Fattura findById(@PathVariable UUID fatturaId) {
		return fatturaService.findById(fatturaId);
	}

	@PutMapping("/{fatturaId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Fattura updateFattura(@PathVariable UUID fatturaId, @RequestBody NewFatturaPayload body) {
		return fatturaService.findByIdAndUpdate(fatturaId, body);
	}

	@DeleteMapping("/{fatturaId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFattura(@PathVariable UUID fatturaId) {
		fatturaService.findByIdAndDelete(fatturaId);
	}

	@GetMapping("/filtra/fatturaCliente")
	public Page<Fattura> filtraFatturaPerClienti(@RequestParam(required = false) UUID id,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return fatturaService.filtraFatturaPerCliente(id, pageable);
	}

	@GetMapping("/filtra/data")
	public Page<Fattura> filtraFatturaByData(@RequestParam(required = false) LocalDate data,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return fatturaService.filtraFatturaPerData(data, pageable);
	}

	@GetMapping("/filtra/anno")
	public Page<Fattura> filtraFatturePerAnno(@RequestParam int anno, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return fatturaService.filtraFatturaPerAnno(anno, pageable);
	}

	@GetMapping("/filtra/stato")
	public Page<Fattura> filtraFatturePerStato(@RequestParam Stato stato, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return fatturaService.filtraFatturaPerStato(stato, pageable);
	}

	@GetMapping("/filtra/importo")
	public Page<Fattura> filtraFatturePerImporto(@RequestParam BigDecimal minImporto,
			@RequestParam BigDecimal maxImporto, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return fatturaService.filtraFatturaPerImporto(minImporto, maxImporto, pageable);
	}
}
