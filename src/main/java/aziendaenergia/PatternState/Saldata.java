package aziendaenergia.PatternState;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;

public class Saldata extends  StatoAbs {

	Saldata(Fattura fattura) {
		super(fattura);
		
	}

	@Override
	public void tipo() {
		 fattura.setStato(Stato.SALDATA);
		
	}

}
