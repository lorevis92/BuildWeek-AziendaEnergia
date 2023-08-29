package aziendaenergia.payload;

import java.math.BigDecimal;
import java.time.LocalDate;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Cliente;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewFatturaPayload {

	@NotNull(message = "L'intestazione è obbligatorio")
	private String intestazione;
	@NotNull(message = "L'anno è obbligatorio")
	private int anno;
	@NotNull(message = "La data è obbligatorio")
	private LocalDate data;
	@NotNull(message = "L'importo è obbligatorio")
	private BigDecimal importo;
	@NotNull(message = "Il numero è obbligatorio")
	private int numero;
	@NotNull(message = "lo stato è obbligatorio")
	private Stato stato;
	@NotNull(message = "Il cliente è obbligatorio")
	private Cliente cliente;
}
