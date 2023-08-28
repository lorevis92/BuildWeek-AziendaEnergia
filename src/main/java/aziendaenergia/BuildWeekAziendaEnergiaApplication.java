package aziendaenergia;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import aziendaenergia.Enum.Stato;
import aziendaenergia.entities.Fattura;

@SpringBootApplication
public class BuildWeekAziendaEnergiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildWeekAziendaEnergiaApplication.class, args);
		
		Fattura f1 = new Fattura("Ciao", 2023, LocalDate.of(2023, 8, 28), new BigDecimal("300.25"), 45, Stato.INSOLUTA );
		f1.inviaMessaggio(f1);
	}
	
	
	

}
