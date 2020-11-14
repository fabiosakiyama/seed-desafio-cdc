package br.com.casadocodigo.domain.autor.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.domain.autor.dto.NovoAutorRequest;
import br.com.casadocodigo.domain.autor.model.Autor;
import br.com.casadocodigo.domain.autor.repository.AutorRepository;

/*
 * 3 (max 7)
 */
@RestController
@RequestMapping("/autores")
public class AutorController {
	
	//1
	private AutorRepository autorRepository;

	public AutorController(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}

	//2
	@PostMapping
	public ResponseEntity<Autor> salvaAutor(@Valid @RequestBody NovoAutorRequest request){
		Autor autorSalvo = autorRepository.save(request.toModel());
		return ResponseEntity.ok(autorSalvo);
	}
}
