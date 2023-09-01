package aziendaenergia.entities;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import aziendaenergia.Enum.Stato;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Fattura {
	@Id
	@GeneratedValue
	private UUID id;
	private String intestazione;
	private int anno;
	private LocalDate data;
	private BigDecimal importo;
	private int numero;
	@Enumerated(EnumType.STRING)
	private Stato stato;
	@ManyToOne
	private Cliente cliente;

	public Fattura(String intestazione, int anno, LocalDate data, BigDecimal importo, int numero, Stato stato,
			Cliente cliente) {
		this.intestazione = intestazione;
		this.anno = anno;
		this.data = data;
		this.importo = importo;
		this.numero = numero;
		this.stato = stato;
		this.cliente = cliente;
	}

	public void inviaMessaggio(Fattura fattura) throws IOException {
		switch (fattura.getStato()) {

		case EMESSA:

			System.out.println(fattura.getCliente().getNomeContatto() + " " + fattura.getCliente().getCognomeContatto()
					+ " La fattura " + fattura.getId() + " e' stata emessa");
			fattura.invioEmail(fattura);

			break;
		case SALDATA:

			System.out.println("La fattura " + fattura.getId() + " e' stata saldata");
			fattura.invioEmail(fattura);
			break;

		case SOSPESA:
			System.out.println("La fattura " + fattura.getId() + " e' stata sospesa");
			fattura.invioEmail(fattura);
			break;

		case INSOLUTA:
			System.out.println(fattura.getCliente().getNomeContatto() + " " + fattura.getCliente().getCognomeContatto()
					+ " La fattura " + fattura.getId() + " non e' stata pagata");
			fattura.invioEmail(fattura);
			break;

		default:
			System.out.println("Errore nella Fattura " + fattura.getId());

		}
	}

	public void invioEmail(Fattura fattura) throws IOException {

		Email from = new Email("lucabjjiannice@gmail.com");
		String subject = "Sending with SendGrid is Fun";
		Email to = new Email("luca.iannice@icloud.com");
		Content content = new Content("text/plain",
				"La tua fattura " + fattura.getNumero() + " è stata " + fattura.getStato());
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		System.out.println("SENDGRID_API_KEY: " + System.getenv("SENDGRID_API_KEY"));

		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}
}
