package aziendaenergia.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import aziendaenergia.entities.User;
import aziendaenergia.exceptions.BadRequestException;
import aziendaenergia.exceptions.NotFoundException;
import aziendaenergia.payload.NewUserPayload;
import aziendaenergia.repositories.UserRepository;

@Service
public class UsersService {

	@Autowired
	UserRepository userRepository;

	// SALVA NUOVO UTENTE + ECCEZIONE SE VIENE USATA LA STESSA EMAIL
	public User save(NewUserPayload body) {
		userRepository.findByEmail(body.getEmail()).ifPresent(user -> {
			throw new BadRequestException("L'email " + body.getEmail() + " è gia stata utilizzata");
		});
		User newUser = new User(body.getUsername(), body.getName(), body.getSurname(), body.getEmail(),
				body.getPassword());
		return userRepository.save(newUser);
	}

	// TORNA LA LISTA DEGLI UTENTI
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	// TORNA LA LISTA DEGLI UTENTI CON L'IMPAGINAZIONE
	public Page<User> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return userRepository.findAll(pageable);
	}

	// CERCA UTENTE TRAMITE ID
	public User findById(UUID id) throws NotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA UTENTE TRAMITE ID
	public User findByIdAndUpdate(UUID id, NewUserPayload body) throws NotFoundException {
		User found = this.findById(id);
		found.setName(body.getName());
		found.setSurname(body.getSurname());
		found.setEmail(body.getEmail());
		return userRepository.save(found);
	}

	// CERCA E CANCELLA UTENTE TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		userRepository.delete(found);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}
	
	//METODO PER IL TEST LOGIN
	 public static boolean authenticateUser(User inputUser, User userFromDatabase, BCryptPasswordEncoder passwordEncoder) {
	        if (inputUser == null || userFromDatabase == null) {
	            return false;
	        }

	        if (!inputUser.getEmail().equals(userFromDatabase.getEmail())) {
	            return false;
	        }

	        String rawPassword = inputUser.getPassword();
	        String encryptedPasswordFromDatabase = userFromDatabase.getPassword();

	        return passwordEncoder.matches(rawPassword, encryptedPasswordFromDatabase);
	    }
}
