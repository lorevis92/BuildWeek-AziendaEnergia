package aziendaenergia.PatternState;

import org.springframework.beans.factory.annotation.Autowired;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;
import aziendaenergia.repositories.FatturaRepository;

public class Emessa extends StatoAbs {
	@Autowired
	FatturaRepository fatturaRepository;

	Emessa(Fattura fattura) {
		super(fattura);

	}

	@Override
	public void tipo() {
		fattura.setStato(Stato.EMESSA);
		fattura.inviaMessaggio(fattura);
		fatturaRepository.save(fattura);

	}

}
