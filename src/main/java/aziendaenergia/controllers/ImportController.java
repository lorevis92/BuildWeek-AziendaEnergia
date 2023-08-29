package aziendaenergia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aziendaenergia.service.ComuniService;
import aziendaenergia.service.ImportService;

@RestController
@RequestMapping("/import")
public class ImportController {
	@Autowired
	private ImportService importService;
	
	@Autowired
	private ComuniService comuniService;

	@PostMapping("/data")
	public ResponseEntity<String> importData() {
		importService.importDataFromCSV("province-italiane.csv");
		return ResponseEntity.ok("Importazione completata!!");
	}
	
	@PostMapping("/dataComuni")
	public ResponseEntity<String> importDataComuni() {
		comuniService.importDataFromCSV("comuni-italiani.csv");
		return ResponseEntity.ok("Importazione comuni completata!!");
	}
}