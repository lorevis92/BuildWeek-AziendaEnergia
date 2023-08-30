package aziendaenergia.payload;

import java.time.LocalDate;

import aziendaenergia.Enum.Tipo;
import aziendaenergia.entities.Indirizzo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewClientePayload {

	@NotNull(message = "La ragioneSociale è obbligatorio")
	private String ragioneSociale;
	@NotNull(message = "La partitaIva è obbligatorio")
	private String partitaIva;
	@NotNull(message = "L'email è obbligatorio")
	private String email;
	@NotNull(message = "La dataInserimento è obbligatorio")
	private LocalDate dataInserimento;
	@NotNull(message = "La dataUltimoContatto è obbligatorio")
	private LocalDate dataUltimoContatto;
	@NotNull(message = "Il fatturatoAnnuale è obbligatorio")
	private Double fatturatoAnnuale;
	@NotNull(message = "Il pec è obbligatorio")
	private String pec;
	@NotNull(message = "Il telefono è obbligatorio")
	private int telefono;
	@NotNull(message = "L'emailContatto è obbligatorio")
	private String emailContatto;
	@NotNull(message = "Il nomeContatto è obbligatorio")
	private String nomeContatto;
	@NotNull(message = "Il cognomeContatto è obbligatorio")
	private String cognomeContatto;
	@NotNull(message = "Il telefonoContatto è obbligatorio")
	private String telefonoContatto;
	@NotNull(message = "Il tipo è obbligatorio")
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	@NotNull(message = "L'indirizzo della sede legale è obbligatorio")
	private Indirizzo sedeLegale;
	@NotNull(message = "L'indirizzo della sede operativa è obbligatorio")
	private Indirizzo sedeOperativa;
}
