package aziendaenergia.entities;

import aziendaenergia.interfaces.AllertaFattura;
import lombok.ToString;

@ToString
public class CentroAssistenza implements AllertaFattura {

	@Override
	public void messaggio(Fattura fattura) {

		switch (fattura.getStato()) {
		case EMESSA:

			System.out.println(fattura.getCliente().getNomeContatto() + " " + fattura.getCliente().getCognomeContatto()
					+ " La fattura " + fattura.getId() + " e' stata emessa");

			break;
		case SALDATA:

			System.out.println("La fattura " + fattura.getId() + " e' stata saldata");

			break;

		case SOSPESA:
			System.out.println("La fattura " + fattura.getId() + " e' stata sospesa");

			break;

		case INSOLUTA:
			System.out.println(fattura.getCliente().getNomeContatto() + " " + fattura.getCliente().getCognomeContatto()
					+ " La fattura " + fattura.getId() + " non e' stata pagata");

			break;

		default:
			System.out.println("Errore nella Fattura " + fattura.getId());

		}
	}
}
