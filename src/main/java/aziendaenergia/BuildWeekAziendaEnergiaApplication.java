package aziendaenergia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

//@EnableScheduling
@SpringBootApplication
public class BuildWeekAziendaEnergiaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BuildWeekAziendaEnergiaApplication.class, args);
		
//		    Email from = new Email("lucabjjiannice@gmail.com");
//		    String subject = "Sending with SendGrid is Fun";
//		    Email to = new Email("luca.iannice@icloud.com");
//		    Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
//		    Mail mail = new Mail(from, subject, to, content);
//
//		    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
//		    System.out.println("SENDGRID_API_KEY: " + System.getenv("SENDGRID_API_KEY"));
//
//		    Request request = new Request();
//		    try {
//		      request.setMethod(Method.POST);
//		      request.setEndpoint("mail/send");
//		      request.setBody(mail.build());
//		      Response response = sg.api(request);
//		      System.out.println(response.getStatusCode());
//		      System.out.println(response.getBody());
//		      System.out.println(response.getHeaders());
//		    } catch (IOException ex) {
//		      throw ex;
//		    }
		  }
	}
	
