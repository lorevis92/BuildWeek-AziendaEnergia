package aziendaenergia.PatternState;

import org.springframework.beans.factory.annotation.Autowired;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;
import aziendaenergia.repositories.FatturaRepository;

public class Sospesa extends StatoAbs {
	@Autowired
	FatturaRepository fatturaRepository;

	Sospesa(Fattura fattura) {
		super(fattura);

	}

	@Override
	public void tipo() {
		fattura.setStato(Stato.SOSPESA);
		fattura.inviaMessaggio(fattura);
		fatturaRepository.save(fattura);
	}

}
