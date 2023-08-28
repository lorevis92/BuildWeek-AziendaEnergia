package aziendaenergia.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import aziendaenergia.Enum.Tipo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente {
	@Id
	@GeneratedValue
	private UUID Id;
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
	private int telefonoContatto;
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	@OneToMany(mappedBy = "cliente")
	private List<Indirizzo> listaIndirizzi = new ArrayList<>();
	@OneToMany(mappedBy = "cliente")
	private List<Fattura> listaFatture = new ArrayList<>();
	
	

}
