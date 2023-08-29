package aziendaenergia.PatternState;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;

public class Insoluta extends StatoAbs {

	Insoluta(Fattura fattura) {
		super(fattura);

	}

	@Override
	public void tipo() {
		fattura.setStato(Stato.INSOLUTA);
		fattura.inviaMessaggio(fattura);
	}

}
