package br.com.casadocodigo.pais.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.pais.dto.NovoPaisRequest;
import br.com.casadocodigo.pais.dto.PaisResponse;
import br.com.casadocodigo.pais.repository.PaisRepository;

/*
 * 4 (max 7)
 */
@RestController
@RequestMapping("/paises")
public class PaisController {

	//1
	private PaisRepository repository;
	
	public PaisController(PaisRepository repository) {
		this.repository = repository;
	}

	//1
	@PostMapping
	public ResponseEntity<Void> salvaPais(@Valid @RequestBody NovoPaisRequest request){
		repository.save(request.toModel());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	//2
	@GetMapping
	public ResponseEntity<List<PaisResponse>> buscaTodosPaises(){
		return ResponseEntity.ok(repository.findAll().stream().map(PaisResponse::new).collect(Collectors.toList()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaPais(@PathVariable Long id){
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
