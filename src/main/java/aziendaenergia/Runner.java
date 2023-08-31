package aziendaenergia;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import aziendaenergia.converterfile.CsvConverter;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	CsvConverter converter;

	@Override
	public void run(String... args) throws Exception {

		try {
			converter.convertCvs("comuni-italiani.csv");
			converter.convertCvs("province-italiane.csv");
			converter.linkProvinceEComuni();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}