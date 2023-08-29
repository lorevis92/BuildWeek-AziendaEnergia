package aziendaenergia.PatternState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import aziendaenergia.service.FatturaService;

@Component
public class CronJob {

    @Autowired
    private FatturaService fatturaService;

    @Scheduled(cron = "0 0 0 * * MON-FRI") //Lo faccio partire ogni 24 ore da lunedi a venerdi. 
    // secondi, minuti, ore, giorni, mesi, giorno della settimana.
    public void checkAndUpdateFatturaStates() {
        fatturaService.checkAndUpdateFatturaStates();
    }
}
