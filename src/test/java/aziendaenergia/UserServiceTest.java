package aziendaenergia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import aziendaenergia.entities.User;
import aziendaenergia.service.UsersService;


@SpringBootTest
public class UserServiceTest {

	     @Autowired
	     private BCryptPasswordEncoder passwordEncoder;
	
	
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
		
		    @Test
		    public void testPasswordMatching() {
		        String rawPassword = "userPassword";
		        String encryptedPassword = passwordEncoder.encode(rawPassword);

		        // Simuliamo il recupero dell'utente dal database
		        User user = new User();
		        user.setUsername("testUser");
		        user.setPassword(encryptedPassword);

		        // Verifica se la password fornita corrisponde alla password criptata nel database
		        boolean passwordMatches = passwordEncoder.matches(rawPassword, user.getPassword());
		        assertTrue(passwordMatches);
		    }
		    
		    @Test
		    public void testPasswordNotMatching() {
		        String rawPassword = "userPassword";
		        String encryptedPassword = passwordEncoder.encode(rawPassword);

		        // Simuliamo il recupero dell'utente dal database
		        User user = new User();
		        user.setUsername("testUser");
		        user.setPassword(encryptedPassword);

		       
		        //boolean passwordMatches = passwordEncoder.matches(rawPassword, user.getPassword());
		        assertFalse(passwordEncoder.matches("wrongPassword", user.getPassword()));	
		        }
		    
		    
		    @Test
		    public void testCorrectLogin() {
		        // Simulate retrieving user from the database
		        User userFromDatabase = new User();
		        userFromDatabase.setEmail("luca@gmail.com"); // Assuming the username is the email
		        userFromDatabase.setPassword(passwordEncoder.encode("Infedele1980!"));

		        // Simulate the login process
		        boolean isAuthenticated = UsersService.authenticateUser(user, userFromDatabase, passwordEncoder);

		        assertTrue(isAuthenticated);
		    }
		    

		    @Test
		    public void testIncorrectLogin() {
		        // Simulate retrieving user from the database
		        User userFromDatabase = new User();
		        userFromDatabase.setEmail("luca@gmail.com"); // Assuming the username is the email
		        userFromDatabase.setPassword(passwordEncoder.encode("Infedele1980!!"));

		        // Simulate the login process
		        boolean isAuthenticated = UsersService.authenticateUser(user, userFromDatabase, passwordEncoder);

		        assertFalse(isAuthenticated);
		    }
		}
		 
		 


