package aziendaenergia.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comuni")
public class Comune {
	@Id
	@GeneratedValue
	private UUID id;
	private int codice_provincia;
	private int progressivo_comune;
	private String denominazione;
	private String nome_provincia;

	@ManyToOne
	@JoinColumn(name = "provincia_id")
	private Provincia provincia;

	public Comune(int codice_provincia, int progressivo_comune, String denominazione, String nome_provincia) {
		this.codice_provincia = codice_provincia;
		this.progressivo_comune = progressivo_comune;
		this.denominazione = denominazione;
		this.nome_provincia = nome_provincia;
	}

}