package aziendaenergia.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	@Column(name = "codice_provincia")
	private String codice;

	@Column(name = "prgressivo_del_comune")
	private String progressivo;

	@Column(name = "denominazione_in_italiano")
	private String denominazione;
	
	@Column(name = "provincia")
	private String provincia;
}
