package aziendaenergia.service;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import aziendaenergia.entities.Provincia;
import aziendaenergia.repositories.ProvinceRepository;

@Service
public class ImportService {

	private final ProvinceRepository provinceRepository;

	public ImportService(ProvinceRepository provinceRepository) {
		this.provinceRepository = provinceRepository;
	}

	public void importDataFromCSV() {
		try {
			ClassPathResource provinceResource = new ClassPathResource("province-italiane.csv");

			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			CSVReader provinceReader = new CSVReaderBuilder(new FileReader(provinceResource.getFile()))
					.withCSVParser(parser).build();

			provinceReader.skip(1);

			String[] provinceLine;
			while ((provinceLine = provinceReader.readNext()) != null) {
				Provincia provincia = new Provincia();
				provincia.setSigla(provinceLine[0]);
				provincia.setProvincia(provinceLine[1]);
				provincia.setRegione(provinceLine[2]);
				provinceRepository.save(provincia);
			}

			provinceReader.close();

			System.out.println("Importazione delle province completata.");
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}
}