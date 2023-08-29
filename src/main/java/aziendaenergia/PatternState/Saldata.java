package aziendaenergia.PatternState;

import org.springframework.beans.factory.annotation.Autowired;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;
import aziendaenergia.repositories.FatturaRepository;

public class Saldata extends StatoAbs {

	@Autowired
	FatturaRepository fatturaRepository;
	
	Saldata(Fattura fattura) {
		super(fattura);

	}

	@Override
	public void tipo() {
		fattura.setStato(Stato.SALDATA);
		fattura.inviaMessaggio(fattura);
		 fatturaRepository.save(fattura);
	}

}
