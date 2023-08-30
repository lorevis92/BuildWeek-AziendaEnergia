package aziendaenergia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aziendaenergia.entities.User;

public class UserTest {
	
	private User user;
	
	 @BeforeEach
	    public void setUp() {
	        
		 user = new User("Lukic", "Luca", "Iannice", "luca@gmail.com", "Infedele1980!");
		 
	 }
		 @Test
		    public void testName() {
		        assertEquals("Luca", user.getName());
		    }

		 @Test
		    public void testEmail() {
		        assertEquals("luca@gmail.com", user.getEmail());
		    }

}

