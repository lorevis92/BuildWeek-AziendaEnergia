package aziendaenergia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aziendaenergia.service.ImportService;

@RestController
@RequestMapping("/import")
public class ImportController {
	@Autowired
	private ImportService importService;

	@PostMapping("/data")
	public ResponseEntity<String> importData() {
		importService.importDataFromCSV("province-italiane.csv");
		return ResponseEntity.ok("Importazione completata");
	}
}