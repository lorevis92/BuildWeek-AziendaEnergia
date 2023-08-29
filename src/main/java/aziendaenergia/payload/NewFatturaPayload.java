package aziendaenergia.payload;

import java.math.BigDecimal;
import java.time.LocalDate;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewFatturaPayload {

	private String intestazione;
	private int anno;
	private LocalDate data;
	private BigDecimal importo;
	private int numero;
	private Stato stato;
	private Cliente cliente;
}
