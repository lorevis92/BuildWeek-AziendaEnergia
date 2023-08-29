package aziendaenergia.payload;

import aziendaenergia.entities.Cliente;
import aziendaenergia.entities.Comune;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewIndirizzoPayload {

	private String via;
	private int civico;
	private String località;
	private String cap;
	private Comune comune;
	private Cliente cliente;
}
