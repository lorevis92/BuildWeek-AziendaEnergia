package aziendaenergia.payload;

import aziendaenergia.entities.Cliente;
import aziendaenergia.entities.Comune;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewIndirizzoPayload {

	@NotNull(message = "La via è obbligatorio")
	private String via;
	@NotNull(message = "Il civico è obbligatorio")
	private int civico;
	@NotNull(message = "La località è obbligatorio")
	private String località;
	@NotNull(message = "Il cap è obbligatorio è obbligatorio")
	private String cap;
	@NotNull(message = "Il comune è obbligatorio")
	private Comune comune;
	@NotNull(message = "Il cliente è obbligatorio")
	private Cliente cliente;
}
