package aziendaenergia.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	String sigla;
	String nome;
	String nome_regione;

	public Provincia(String sigla, String nome, String nome_regione) {
		this.sigla = sigla;
		this.nome = nome;
		this.nome_regione = nome_regione;
	}

}
