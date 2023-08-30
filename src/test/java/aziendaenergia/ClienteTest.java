package aziendaenergia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import aziendaenergia.Enum.Tipo;
import aziendaenergia.entities.Cliente;
import aziendaenergia.entities.Indirizzo;


	@SpringBootTest
	public class ClienteTest {

	    private Cliente cliente;

	    @BeforeEach
	    public void setUp() {
	        Indirizzo sedeLegale = new Indirizzo("12345", 1, "City", "Street", null, null);
	        cliente = new Cliente("ACME Corp", "123456789", "acme@example.com", null, null, null, null, 0,
	                "contact@example.com", "John", "Doe", "1234567890", Tipo.PA, sedeLegale, null);
	    }

	    @Test
	    public void testGetRagioneSociale() {
	        assertEquals("ACME Corp", cliente.getRagioneSociale());
	    }

	    @Test
	    public void testGetPartitaIva() {
	        assertEquals("123456789", cliente.getPartitaIva());
	    }

	    @Test
	    public void testGetEmail() {
	        assertEquals("acme@example.com", cliente.getEmail());
	    }

	 
	}
