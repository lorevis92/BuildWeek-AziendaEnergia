package aziendaenergia.service;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import aziendaenergia.entities.Comune;
import aziendaenergia.repositories.ComuneRepository;

@Service
public class ComuniService {

	private final ComuneRepository comuneRepository;

	public ComuniService(ComuneRepository comuneRepository) {
		this.comuneRepository = comuneRepository;
	}

	public void importDataFromCSV(String filePath) {
		try {
			FileReader filereader = new FileReader(filePath);

			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			CSVReader comuneReader = new CSVReaderBuilder(filereader).withCSVParser(parser).build();

			comuneReader.skip(1); // Skip header row

			String[] comuniLine;
			while ((comuniLine =comuneReader.readNext()) != null) {
				Comune comune = new Comune();
				comune.setCodice(comuniLine[0]);
				comune.setProgressivo(comuniLine[1]);
				comune.setDenominazione(comuniLine[2]);
				comune.setProvincia(comuniLine[3]);
				comuneRepository.save(comune);
			}

			comuneReader.close();

			System.out.println("Importazione dei comuni completati.");
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}
}
