package aziendaenergia.payload;

import java.time.LocalDate;

import aziendaenergia.Enum.Tipo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewClientePayload {
	private String ragioneSociale;
	private String partitaIva;
	private String email;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private Double fatturatoAnnuale;
	private String pec;
	private int telefono;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
}
