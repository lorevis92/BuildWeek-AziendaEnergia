package aziendaenergia.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;
import aziendaenergia.exceptions.NotFoundException;
import aziendaenergia.payload.NewFatturaPayload;
import aziendaenergia.repositories.FatturaRepository;

@Service
public class FatturaService {

	@Autowired
	FatturaRepository fatturaRepository;

	// SALVA NUOVA FATTURA
	public Fattura save(NewFatturaPayload body) {
		Fattura newFattura = new Fattura(body.getIntestazione(), body.getAnno(), body.getData(), body.getImporto(),
				body.getNumero(), body.getStato(), body.getCliente());
		return fatturaRepository.save(newFattura);
	}

	// TORNA LA LISTA DELLE FATTURE
	public List<Fattura> getFatture() {
		return fatturaRepository.findAll();
	}

	// TORNA LA LISTA DELLE FATTURE CON L'IMPAGINAZIONE
	public Page<Fattura> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return fatturaRepository.findAll(pageable);
	}

	// CERCA FATTURA TRAMITE ID
	public Fattura findById(UUID id) throws NotFoundException {
		return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA FATTURA TRAMITE ID
	public Fattura findByIdAndUpdate(UUID id, NewFatturaPayload body) throws NotFoundException {
		Fattura found = this.findById(id);
		found.setIntestazione(body.getIntestazione());
		found.setAnno(body.getAnno());
		found.setData(body.getData());
		found.setImporto(body.getImporto());
		found.setNumero(body.getNumero());
		found.setStato(body.getStato());
		return fatturaRepository.save(found);
	}

	// CERCA E CANCELLA UTENTE TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Fattura found = this.findById(id);
		fatturaRepository.delete(found);
	}

	public Page<Fattura> filtraFatturaPerCliente(UUID id, Pageable pageable) {
		return fatturaRepository.findByClienteId(id, pageable);
	}

	public Page<Fattura> filtraFatturaPerData(LocalDate data, Pageable pageable) {
		return fatturaRepository.findByData(data, pageable);
	}

	public Page<Fattura> filtraFatturaPerStato(Stato stato, Pageable pageable) {
		return fatturaRepository.findByStato(stato, pageable);
	}

	public Page<Fattura> filtraFatturaPerAnno(int anno, Pageable pageable) {
		return fatturaRepository.findByAnno(anno, pageable);
	}

	public Page<Fattura> filtraFatturaPerImporto(BigDecimal minImporto, BigDecimal maxImporto, Pageable pageable) {
		return fatturaRepository.findByImportoBetween(minImporto, maxImporto, pageable);
	}

	// ORDINA FATTURE PER NUMERO CRESCENTE
	public Page<Fattura> getFattureOrdinatePerNumero(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("numero"));
		return fatturaRepository.findAll(pageable);
	}

	// ORDINA FATTURA PER IMPORTO DECRESCENTE
	public Page<Fattura> getFattureOrdinatePerImporto(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("importo").descending());
		return fatturaRepository.findAll(pageable);
	}

	// ORDINA FATTURA PER IMPORTO CRESCENTE
	public Page<Fattura> getFattureOrdinatePerData(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("data"));
		return fatturaRepository.findAll(pageable);
	}

	// ORDINA FATTURA PER ANNO CRESCENTE
	public Page<Fattura> getFattureOrdinatePerAnno(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("anno"));
		return fatturaRepository.findAll(pageable);
	}

	// ORDINA FATTURA PER STATO CRESCENTE
	public Page<Fattura> getFattureOrdinatePerStato(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("stato"));
		return fatturaRepository.findAll(pageable);
	}

	public void checkAndUpdateFatturaStates() {
		List<Fattura> emessaFatture = fatturaRepository.findByStatoAndData(Stato.EMESSA, LocalDate.now());

		for (Fattura fattura : emessaFatture) {
			LocalDate currentDate = LocalDate.now();
			LocalDate thirtyDaysAfterData = fattura.getData().plusDays(30);

			if (currentDate.isEqual(fattura.getData())) {
				fattura.inviaMessaggio(fattura); // invia il messaggio al cliente
			} else if (currentDate.isAfter(thirtyDaysAfterData)) {
				fattura.setStato(Stato.INSOLUTA); // cambia lo stato da pagare e poi le faccio inviare il messaggio al
													// cliente
				fattura.inviaMessaggio(fattura);
			}
			fatturaRepository.save(fattura);
		}
	}

}