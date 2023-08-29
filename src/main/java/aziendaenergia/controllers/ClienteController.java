package aziendaenergia.controllers;

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

import aziendaenergia.entities.Cliente;
import aziendaenergia.payload.NewClientePayload;
import aziendaenergia.service.ClienteService;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
	@Autowired
	ClienteService clienteService;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente saveCliente(@RequestBody NewClientePayload body) {
		Cliente createdCliente = clienteService.save(body);
		return createdCliente;
	}

	@GetMapping("")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public Page<Cliente> getCliente(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return clienteService.find(page, size, sortBy);
	}

	@GetMapping("/{id_cliente}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Cliente findById(@PathVariable UUID id_cliente) {
		return clienteService.findById(id_cliente);
	}

	@PutMapping("/{id_cliente}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Cliente updateCliente(@PathVariable UUID id_cliente, @RequestBody NewClientePayload body) {
		return clienteService.findByIdAndUpdate(id_cliente, body);
	}

	@DeleteMapping("/{id_cliente}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCliente(@PathVariable UUID id_cliente) {
		clienteService.findByIdAndDelete(id_cliente);
	}

	@GetMapping("/filtra/fatturato")
	public Page<Cliente> filtraClientiPerFatturato(@RequestParam(required = false) Double minFatturatoAnnuale,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return clienteService.filtraClientiPerFatturato(minFatturatoAnnuale, pageable);
	}

	@GetMapping("/filtra/data-inserimento")
	public Page<Cliente> filtraClientiPerDataInserimento(@RequestParam(required = false) LocalDate dataInserimento,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return clienteService.filtraClientiPerDataInserimento(dataInserimento, pageable);
	}

	@GetMapping("/filtra/data-ultimo-contatto")
	public Page<Cliente> filtraClientiPerDataUltimoContatto(
			@RequestParam(required = false) LocalDate dataUltimoContatto, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return clienteService.filtraClientiPerDataUltimoContatto(dataUltimoContatto, pageable);
	}

	@GetMapping("/filtra/nome")
	public Page<Cliente> filtraClientiPerParteNome(@RequestParam(required = false) String parteNome,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return clienteService.filtraClientiPerParteNome(parteNome, pageable);
	}
}
