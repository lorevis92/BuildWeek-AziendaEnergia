package aziendaenergia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aziendaenergia.entities.Provincia;
import aziendaenergia.service.ProvinciaService;

@RestController
@RequestMapping("/province")
public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaService;

	@GetMapping
	public Page<Provincia> findProvince(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return provinciaService.findProvinceandPage(page, size, sortBy);
	}

	@GetMapping("/{nome_provincia}")
	public Provincia findByNome(String nome_provincia) {
		return provinciaService.findByNome(nome_provincia);
	}

	@GetMapping("/{sigla}")
	public Provincia findBySigla(String sigla) {
		return provinciaService.findBySigla(sigla);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public Provincia createProvincia(Provincia provincia) {
		return provinciaService.saveProvincia(provincia);
	}

}