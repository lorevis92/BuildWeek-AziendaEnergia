package aziendaenergia.PatternState;

import aziendaenergia.entities.Fattura;

public abstract class StatoAbs {
	protected Fattura fattura;

	StatoAbs(Fattura fattura) {
		this.fattura = fattura;
	}

	public abstract void tipo();
}
