package aziendaenergia.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "province")
@Getter
@Setter
@NoArgsConstructor

public class Provincia {

	@Id
	@GeneratedValue
	private UUID id;
	@Column(name = "sigla")
	private String sigla;

	@Column(name = "provincia")
	private String provincia;

	@Column(name = "regione")
	private String regione;
	
	@ManyToOne
	private Comune comune;
}
