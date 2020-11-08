package br.com.casadocodigo.autor.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.autor.dto.NovoAutorRequest;
import br.com.casadocodigo.autor.model.Autor;
import br.com.casadocodigo.autor.repository.AutorRepository;

@RestController
@RequestMapping("/autores")
public class AutorController {
	
	private AutorRepository autorRepository;

	public AutorController(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}

	@PostMapping
	public ResponseEntity<Autor> salvaAutor(@Valid @RequestBody NovoAutorRequest request){
		Autor autorSalvo = autorRepository.save(request.toModel());
		return ResponseEntity.ok(autorSalvo);
	}
}
