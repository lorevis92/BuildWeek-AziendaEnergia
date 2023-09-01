package aziendaenergia.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import aziendaenergia.Enum.Tipo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cliente {
	@Id
	@GeneratedValue
	private UUID id;
	private String ragioneSociale;
	private String partitaIva;
	@Column(nullable = false, unique = true)
	private String email;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private Double fatturatoAnnuale;
	private String pec;
	private int telefono;
	@Column(nullable = false, unique = true)
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	@OneToOne
	@JoinColumn(name = "sede_legale_id")
	private Indirizzo sedeLegale;

	@OneToOne
	@JoinColumn(name = "sede_operativa_id")
	private Indirizzo sedeOperativa;
	@OneToMany(mappedBy = "cliente")
	private List<Fattura> listafatture = new ArrayList<>();

	public Cliente(String ragioneSociale, String partitaIva, String email, LocalDate dataInserimento,
			LocalDate dataUltimoContatto, Double fatturatoAnnuale, String pec, int telefono, String emailContatto,
			String nomeContatto, String cognomeContatto, String telefonoContatto, Tipo tipo, Indirizzo sedeLegale,
			Indirizzo sedeOperativa) {

		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.email = email;
		this.dataInserimento = dataInserimento;
		this.dataUltimoContatto = dataUltimoContatto;
		this.fatturatoAnnuale = fatturatoAnnuale;
		this.pec = pec;
		this.telefono = telefono;
		this.emailContatto = emailContatto;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.tipo = tipo;
		this.sedeLegale = sedeLegale;
		this.sedeOperativa = sedeOperativa;

	}
}
