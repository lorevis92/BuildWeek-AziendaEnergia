package aziendaenergia.PatternState;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;

public class Emessa extends StatoAbs{

	Emessa(Fattura fattura) {
		super(fattura);
		
	}

	@Override
	public void tipo() {
		 fattura.setStato(Stato.EMESSA);
		 fattura.inviaMessaggio(fattura);
		
	}

	

}
