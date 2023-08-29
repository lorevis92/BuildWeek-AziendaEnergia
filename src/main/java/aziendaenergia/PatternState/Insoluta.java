package aziendaenergia.PatternState;

import org.springframework.beans.factory.annotation.Autowired;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;
import aziendaenergia.repositories.FatturaRepository;

public class Insoluta extends StatoAbs {
	@Autowired
	FatturaRepository fatturaRepository;

	Insoluta(Fattura fattura) {
		super(fattura);

	}

	@Override
	public void tipo() {
		fattura.setStato(Stato.INSOLUTA);
		fattura.inviaMessaggio(fattura);
		fatturaRepository.save(fattura);
	}

}
