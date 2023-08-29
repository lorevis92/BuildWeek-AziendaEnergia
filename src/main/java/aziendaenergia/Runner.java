package aziendaenergia;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import aziendaenergia.Enum.Stato;
import aziendaenergia.Enum.Tipo;
import aziendaenergia.entities.Cliente;
import aziendaenergia.payload.NewClientePayload;
import aziendaenergia.payload.NewFatturaPayload;
import aziendaenergia.repositories.UserRepository;
import aziendaenergia.security.AuthController;
import aziendaenergia.service.ClienteService;
import aziendaenergia.service.FatturaService;

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

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 10; i++) {
			String ragioneSociale = faker.company().name();
			String partitaIva = faker.number().digits(11);
			String email = faker.internet().emailAddress();
			LocalDate dataInserimento = LocalDate.now().minusDays(faker.number().numberBetween(1, 365));
			LocalDate dataUltimoContatto = LocalDate.now().minusDays(faker.number().numberBetween(1, 365));
			Double fatturatoAnnuale = faker.number().randomDouble(2, 1000, 1000000);
			String pec = faker.internet().emailAddress();
			int minPhoneNumber = 1000; // 10 cifre, es. 1000000000
			int maxPhoneNumber = 99999; // 10 cifre, es. 9999999999
			int telefono = ThreadLocalRandom.current().nextInt(minPhoneNumber, maxPhoneNumber + 1);
			String emailContatto = faker.internet().emailAddress();
			String nomeContatto = faker.name().firstName();
			String cognomeContatto = faker.name().lastName();
			String telefonoContatto = faker.phoneNumber().phoneNumber();
			Tipo tipo = Tipo.SPA; // O qualsiasi altro valore

			NewClientePayload clientePayload = new NewClientePayload(ragioneSociale, partitaIva, email, dataInserimento,
					dataUltimoContatto, fatturatoAnnuale, pec, telefono, emailContatto, nomeContatto, cognomeContatto,
					telefonoContatto, tipo);

			Cliente cliente = clienteService.save(clientePayload);

			for (int j = 0; j < 3; j++) {
				String intestazione = faker.company().name();
				int anno = faker.number().numberBetween(2000, 2025);
				LocalDate data = LocalDate.now().minusDays(faker.number().numberBetween(1, 365));
				BigDecimal importo = new BigDecimal(faker.number().numberBetween(50, 1000));
				int numero = faker.number().numberBetween(1000, 9999);
				Stato stato = Stato.EMESSA; // O qualsiasi altro valore

				NewFatturaPayload fatturaPayload = new NewFatturaPayload(intestazione, anno, data, importo, numero,
						stato, cliente);

				fatturaService.save(fatturaPayload);
			}
		}
	}
}
