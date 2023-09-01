package aziendaenergia.service;

import java.io.IOException;
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

	public void checkAndUpdateFatturaStates() throws IOException {
		List<Fattura> emessaFatture = fatturaRepository.findByStato(Stato.EMESSA);

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

	public void inviaMessaggio(Fattura fattura) throws IOException {
		switch (fattura.getStato()) {
		case EMESSA:

			System.out.println(fattura.getCliente().getNomeContatto() + " " + fattura.getCliente().getCognomeContatto()
					+ " La fattura " + fattura.getId() + " e' stata emessa " + fattura.getData());
			fattura.invioEmail(fattura);

			break;
		case SALDATA:

			System.out.println("La fattura " + fattura.getId() + " e' stata saldata");
			fattura.invioEmail(fattura);

			break;

		case SOSPESA:
			System.out.println("La fattura " + fattura.getId() + " e' stata sospesa");
			fattura.invioEmail(fattura);

			break;

		case INSOLUTA:
			System.out.println(fattura.getCliente().getNomeContatto() + " " + fattura.getCliente().getCognomeContatto()
					+ " La fattura " + fattura.getId() + " non e' stata pagata");
			fattura.invioEmail(fattura);

			break;

		default:
			System.out.println("Errore nella Fattura " + fattura.getId());

		}
	}

	public Fattura tipoSospesa(UUID id) throws IOException {
		Fattura found = this.findById(id);
		found.setStato(Stato.SOSPESA);
		found.inviaMessaggio(found);
		return fatturaRepository.save(found);
	}

	public Fattura tipoInsoluta(UUID id) throws IOException {
		Fattura found = this.findById(id);
		found.setStato(Stato.INSOLUTA);
		found.inviaMessaggio(found);
		return fatturaRepository.save(found);
	}

	public Fattura tipoSaldata(UUID id) throws IOException {
		Fattura found = this.findById(id);
		found.setStato(Stato.SALDATA);
		found.inviaMessaggio(found);
		return fatturaRepository.save(found);
	}

	public Fattura tipoEmessa(UUID id) throws IOException {
		Fattura found = this.findById(id);
		found.setStato(Stato.EMESSA);
		found.inviaMessaggio(found);
		return fatturaRepository.save(found);
	}

}