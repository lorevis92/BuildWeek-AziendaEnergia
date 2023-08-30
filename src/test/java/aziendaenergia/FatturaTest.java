package aziendaenergia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aziendaenergia.Enum.Stato;
import aziendaenergia.Enum.Tipo;
import aziendaenergia.entities.Cliente;
import aziendaenergia.entities.Fattura;
import aziendaenergia.entities.Indirizzo;

public class FatturaTest {
	
	 private Cliente cliente;
	 private Fattura fattura;
	 private Indirizzo sedeLegale;

	    @BeforeEach
	    public void setUp() {
	        
	    	sedeLegale = new Indirizzo("12345", 1, "City", "Street", null, null);
	    	
	        cliente = new Cliente("ACME Corp", "123456789", "acme@example.com", null, null, null, null, 0,
	                "contact@example.com", "John", "Doe", "1234567890", Tipo.PA, sedeLegale, null);
	    	
	        fattura = new Fattura("Ciao", 2022, LocalDate.of(2022,6,23), new BigDecimal("579.88"), 67, Stato.EMESSA, cliente);
	    }

	    @Test
	    public void testGetIntestazione() {
	        assertEquals("Ciao", fattura.getIntestazione());
	    }

	    @Test
	    public void testGetLocalDate() {
	        assertEquals(LocalDate.of(2022,6,23), fattura.getData());
	    }

	    @Test
	    public void testImporto() {
	        assertEquals(new BigDecimal("579.88"), fattura.getImporto());
	    }

	 
	}


