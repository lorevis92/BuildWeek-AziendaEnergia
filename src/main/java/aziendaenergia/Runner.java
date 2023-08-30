package aziendaenergia;

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
import aziendaenergia.entities.Comune;
import aziendaenergia.entities.Provincia;
import aziendaenergia.repositories.ComuneRepository;
import aziendaenergia.repositories.ProvinceRepository;
import aziendaenergia.repositories.UserRepository;
import aziendaenergia.security.AuthController;
import aziendaenergia.service.ClienteService;
import aziendaenergia.service.ComuniService;
import aziendaenergia.service.FatturaService;
import aziendaenergia.service.ImportService;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	AuthController authController;

	@Autowired
	UserRepository utenteRepo;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private FatturaService fatturaService;

	@Autowired
	private ComuneRepository comuneRepository;

	@Autowired
	private ProvinceRepository provinciaRepository;

	@Autowired
	private ImportService importService;

	@Autowired
	private ComuniService comuniService;

	@Override
	public void run(String... args) throws Exception {

		importService.importDataFromCSV("province-italiane.csv");

		comuniService.importDataFromCSV("comuni-italiani.csv");

		Faker faker = new Faker(new Locale("it"));

		List<Comune> comuni = comuneRepository.findAll();
//		if (comuni.isEmpty()) {
//			comuniService.importDataFromCSV("comuni-italiani.csv");
//		}
		List<Provincia> province = provinciaRepository.findAll();
//		if (comuni.isEmpty()) {
//			importService.importDataFromCSV("province-italiane.csv");
//		}
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

//			NewClientePayload clientePayload = new NewClientePayload(ragioneSociale, partitaIva, email, dataInserimento,
//					dataUltimoContatto, fatturatoAnnuale, pec, telefono, emailContatto, nomeContatto, cognomeContatto,
//					telefonoContatto, tipo, null, null);
//
//			Cliente cliente = clienteService.save(clientePayload);

			// CREAZIONE INDIRIZZO SEDE LEGALE
			Comune comuneSedeLegale = comuni.get(faker.number().numberBetween(0, comuni.size()));
			String viaSedeLegale = faker.address().streetName();
			int civicoSedeLegale = faker.number().numberBetween(1, 100);
			String localitaSedeLegale = comuneSedeLegale.getProvincia();
			String capSedeLegale = faker.address().zipCode();
//			Indirizzo indirizzoSedeLegale = new Indirizzo(capSedeLegale, civicoSedeLegale, localitaSedeLegale,
//					viaSedeLegale, cliente, comuneSedeLegale);

			// CREAZIONE INDIRIZZO SEDE OPERATIVA
			Comune comuneSedeOperativa = comuni.get(faker.number().numberBetween(0, comuni.size()));
			String viaSedeOperativa = faker.address().streetName();
			int civicoSedeOperativa = faker.number().numberBetween(1, 100);
			String localitaSedeOperativa = comuneSedeOperativa.getProvincia();
			String capSedeOperativa = faker.address().zipCode();
//			Indirizzo indirizzoSedeOperativa = new Indirizzo(capSedeOperativa, civicoSedeOperativa,
//					localitaSedeOperativa, viaSedeOperativa, cliente, comuneSedeOperativa);
//
//			cliente.setSedeLegale(indirizzoSedeLegale);
//			cliente.setSedeOperativa(indirizzoSedeOperativa);
//
//			clienteService.save(cliente);

			// CREAZIONE FATTURE
			for (int j = 0; j < 3; j++) {
				String intestazione = faker.company().name();
				int anno = faker.number().numberBetween(2000, 2025);
				LocalDate data = LocalDate.now().minusDays(faker.number().numberBetween(1, 365));
				BigDecimal importo = new BigDecimal(faker.number().numberBetween(50, 1000));
				int numero = faker.number().numberBetween(1000, 9999);
				Stato stato = Stato.EMESSA;

//				NewFatturaPayload fatturaPayload = new NewFatturaPayload(intestazione, anno, data, importo, numero,
//						stato, cliente);
//
//				fatturaService.save(fatturaPayload);
			}
		}
	}
}
