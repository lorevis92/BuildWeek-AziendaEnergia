package aziendaenergia;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import aziendaenergia.Enum.Stato;
import aziendaenergia.Enum.Tipo;
import aziendaenergia.converterfile.CsvConverter;
import aziendaenergia.entities.Cliente;
import aziendaenergia.entities.Comune;
import aziendaenergia.entities.Indirizzo;
import aziendaenergia.entities.User;
import aziendaenergia.payload.NewClientePayload;
import aziendaenergia.payload.NewFatturaPayload;
import aziendaenergia.payload.NewUserPayload;
import aziendaenergia.repositories.ClienteRepository;
import aziendaenergia.repositories.ComuneRepository;
import aziendaenergia.repositories.FatturaRepository;
import aziendaenergia.repositories.IndirizzoRepository;
import aziendaenergia.repositories.ProvinciaRepository;
import aziendaenergia.repositories.UserRepository;
import aziendaenergia.security.AuthController;
import aziendaenergia.service.ClienteService;
import aziendaenergia.service.FatturaService;
import aziendaenergia.service.UsersService;
import jakarta.transaction.Transactional;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	CsvConverter converter;

	@Autowired
	UsersService userService;

	@Autowired
	UserRepository utenteRepo;

	@Autowired
	AuthController authController;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	FatturaRepository fatturaRepository;

	@Autowired
	IndirizzoRepository indirizzoRepository;

	@Autowired
	ClienteService clienteService;

	@Autowired
	ComuneRepository comuneRepository;

	@Autowired
	ProvinciaRepository provinciaRepository;

	@Autowired
	FatturaService fatturaService;

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		List<Comune> comuni = comuneRepository.findAll();
		if (comuni.isEmpty()) {
			try {
				converter.convertCvs("comuni-italiani.csv");
				converter.convertCvs("province-italiane.csv");
				converter.linkProvinceEComuni();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		List<User> utentiDb = utenteRepo.findAll();
		if (utentiDb.isEmpty()) {

			for (int i = 0; i < 10; i++) {

				String name = faker.name().firstName();
				String surname = faker.name().lastName();
				String username = name.toLowerCase() + "_" + surname.toLowerCase();
				String email = name.toLowerCase() + "." + surname.toLowerCase() + "@email.com";
				String password = "1234";
				NewUserPayload user = new NewUserPayload(username, name, surname, email, password);
				authController.saveUser(user);

			}
		}

		// CREAZIONE CLIENTE
		for (int i = 0; i < 10; i++) {
			String ragioneSociale = faker.company().name();
			String partitaIva = faker.number().digits(11);
			String email = faker.internet().emailAddress();
			LocalDate dataInserimento = LocalDate.now().minusDays(faker.number().numberBetween(1, 365));
			LocalDate dataUltimoContatto = LocalDate.now().minusDays(faker.number().numberBetween(1, 365));
			Double fatturatoAnnuale = faker.number().randomDouble(2, 1000, 1000000);
			String pec = faker.internet().emailAddress();
			int minPhoneNumber = 1000;
			int maxPhoneNumber = 99999;
			int telefono = ThreadLocalRandom.current().nextInt(minPhoneNumber, maxPhoneNumber + 1);
			String emailContatto = faker.internet().emailAddress();
			String nomeContatto = faker.name().firstName();
			String cognomeContatto = faker.name().lastName();
			String telefonoContatto = faker.phoneNumber().phoneNumber();
			Tipo tipo = Tipo.SPA;

			NewClientePayload clientePayload = new NewClientePayload(ragioneSociale, partitaIva, email, dataInserimento,
					dataUltimoContatto, fatturatoAnnuale, pec, telefono, emailContatto, nomeContatto, cognomeContatto,
					telefonoContatto, tipo, null, null);

			Cliente cliente = clienteService.save(clientePayload);

			// CREAZIONE INDIRIZZO SEDE LEGALE
			if (!comuni.isEmpty()) {
				Comune comuneSedeLegale = comuni.get(faker.number().numberBetween(0, comuni.size()));
				String viaSedeLegale = faker.address().streetName();
				int civicoSedeLegale = faker.number().numberBetween(1, 100);
				String localitaSedeLegale = comuneSedeLegale.getNome_provincia();
				String capSedeLegale = faker.address().zipCode();
				Indirizzo indirizzoSedeLegale = new Indirizzo(capSedeLegale, civicoSedeLegale, localitaSedeLegale,
						viaSedeLegale, cliente, comuneSedeLegale);
				indirizzoRepository.save(indirizzoSedeLegale);
				cliente.setSedeLegale(indirizzoSedeLegale);
			}

			// CREAZIONE INDIRIZZO SEDE OPERATIVA
			if (!comuni.isEmpty()) {
				Comune comuneSedeOperativa = comuni.get(faker.number().numberBetween(0, comuni.size()));
				String viaSedeOperativa = faker.address().streetName();
				int civicoSedeOperativa = faker.number().numberBetween(1, 100);
				String localitaSedeOperativa = comuneSedeOperativa.getNome_provincia();
				String capSedeOperativa = faker.address().zipCode();
				Indirizzo indirizzoSedeOperativa = new Indirizzo(capSedeOperativa, civicoSedeOperativa,
						localitaSedeOperativa, viaSedeOperativa, cliente, comuneSedeOperativa);

				indirizzoRepository.save(indirizzoSedeOperativa);
				cliente.setSedeOperativa(indirizzoSedeOperativa);
			}

			clienteService.save(cliente);

			// CREAZIONE FATTURE
			for (int j = 0; j < 3; j++) {
				String intestazione = faker.company().name();
				int anno = faker.number().numberBetween(2000, 2025);
				LocalDate data = LocalDate.now().minusDays(faker.number().numberBetween(1, 365));
				BigDecimal importo = new BigDecimal(faker.number().numberBetween(50, 1000));
				int numero = faker.number().numberBetween(1000, 9999);
				Stato stato = Stato.EMESSA;

				NewFatturaPayload fatturaPayload = new NewFatturaPayload(intestazione, anno, data, importo, numero,
						stato, cliente);

				fatturaService.save(fatturaPayload);

			}
		}
	}
}