package aziendaenergia.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import aziendaenergia.Enum.Stato;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Fattura {
	@Id
	@GeneratedValue
	private UUID id;
	private String intestazione;
	private int anno;
	private LocalDate data;
	private BigDecimal importo;
	private int numero;
	@Enumerated(EnumType.STRING)
	private Stato stato;
	@ManyToOne
	private Cliente cliente;

	public Fattura(String intestazione, int anno, LocalDate data, BigDecimal importo, int numero, Stato stato,
			Cliente cliente) {
		this.intestazione = intestazione;
		this.anno = anno;
		this.data = data;
		this.importo = importo;
		this.numero = numero;
		this.stato = stato;
		this.cliente = cliente;
	}

	public void inviaMessaggio(Fattura fattura) {

	}

}
