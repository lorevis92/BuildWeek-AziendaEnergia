package aziendaenergia.PatternState;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import aziendaenergia.service.FatturaService;

@Component
public class CronJob {

	@Autowired
	private FatturaService fatturaService;

	@Scheduled(cron = "*/10 * * * * *")
	public void checkAndUpdateFatturaStates() throws IOException {
		System.out.println("Executing checkAndUpdateFatturaStates at: " + LocalDate.now());
		fatturaService.checkAndUpdateFatturaStates();
	}
}
