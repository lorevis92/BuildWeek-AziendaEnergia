package aziendaenergia.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import aziendaenergia.entities.Cliente;
import aziendaenergia.exceptions.BadRequestException;
import aziendaenergia.exceptions.NotFoundException;
import aziendaenergia.payload.NewClientePayload;
import aziendaenergia.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	// SALVA NUOVO CLIENTE + ECCEZIONE SE VIENE USATA LA STESSA EMAIL
	public Cliente save(NewClientePayload body) {
		clienteRepository.findByEmail(body.getEmail()).ifPresent(cliente -> {
			throw new BadRequestException("L'email " + body.getEmail() + " è gia stata utilizzata");
		});
		Cliente newCliente = new Cliente(body.getRagioneSociale(), body.getPartitaIva(), body.getEmail(),
				body.getDataInserimento(), body.getDataUltimoContatto(), body.getFatturatoAnnuale(), body.getPec(),
				body.getTelefono(), body.getEmailContatto(), body.getNomeContatto(), body.getCognomeContatto(),
				body.getTelefonoContatto(), body.getTipo(), body.getSedeLegale(), body.getSedeOperativa());
		return clienteRepository.save(newCliente);
	}

	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	// TORNA LA LISTA DEGLI DEI CLIENTI
	public List<Cliente> getClienti() {
		return clienteRepository.findAll();
	}

	// TORNA LA LISTA DEI CLIENTI CON L'IMPAGINAZIONE
	public Page<Cliente> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return clienteRepository.findAll(pageable);
	}

	// CERCA CLIENTE TRAMITE ID
	public Cliente findById(UUID id) throws NotFoundException {
		return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA CLIENTE TRAMITE ID
	public Cliente findByIdAndUpdate(UUID id, NewClientePayload body) throws NotFoundException {
		Cliente found = this.findById(id);
		found.setRagioneSociale(body.getRagioneSociale());
		found.setPartitaIva(body.getPartitaIva());
		found.setEmail(body.getEmail());
		found.setDataInserimento(body.getDataInserimento());
		found.setDataUltimoContatto(body.getDataUltimoContatto());
		found.setFatturatoAnnuale(body.getFatturatoAnnuale());
		found.setPec(body.getPec());
		found.setTelefono(body.getTelefono());
		found.setEmailContatto(body.getEmailContatto());
		found.setNomeContatto(body.getNomeContatto());
		found.setCognomeContatto(body.getCognomeContatto());
		found.setTelefonoContatto(body.getTelefonoContatto());
		found.setTipo(body.getTipo());
		found.setEmail(body.getEmail());
		return clienteRepository.save(found);
	}

	// CERCA E CANCELLA UTENTE TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Cliente found = this.findById(id);
		clienteRepository.delete(found);
	}

	public Page<Cliente> filtraClientiPerFatturato(Double minFatturatoAnnuale, Pageable pageable) {
		return clienteRepository.findByFatturatoAnnualeGreaterThanEqual(minFatturatoAnnuale, pageable);
	}

	public Page<Cliente> filtraClientiPerDataInserimento(LocalDate dataInserimento, Pageable pageable) {
		return clienteRepository.findByDataInserimento(dataInserimento, pageable);
	}

	public Page<Cliente> filtraClientiPerDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable) {
		return clienteRepository.findByDataUltimoContatto(dataUltimoContatto, pageable);
	}

	public Page<Cliente> filtraClientiPerParteNome(String parteNome, Pageable pageable) {
		return clienteRepository.findByNomeContattoContainingIgnoreCase(parteNome, pageable);
	}

//	public Page<Cliente> getClientiByProvincia(Pageable pageable) {
//		return clienteRepository.findAllByOrderBySedeLegaleProvincia(pageable);
//	}
}
