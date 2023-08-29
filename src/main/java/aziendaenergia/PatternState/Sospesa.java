package aziendaenergia.PatternState;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;

public class Sospesa extends StatoAbs {

	Sospesa(Fattura fattura) {
		super(fattura);

	}

	@Override
	public void tipo() {
		fattura.setStato(Stato.SOSPESA);
		fattura.inviaMessaggio(fattura);
	}

}
