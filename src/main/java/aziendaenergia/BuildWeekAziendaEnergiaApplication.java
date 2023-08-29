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

	}

}