package aziendaenergia.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Indirizzo {

	@Id
	@GeneratedValue
	private UUID indirizzo_id;
	private String via;
	private int civico;
	private String località;
	private String cap;
	@ManyToOne
	@JoinColumn(name = "comune_id", referencedColumnName = "id")
	private Comune comune;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Indirizzo(String cap, int civico, String localita, String via, Cliente cliente, Comune comune) {

		this.cap = cap;
		this.civico = civico;
		this.località = localita;
		this.via = via;
		this.cliente = cliente;
		this.comune = comune;
	}
}
