package br.com.casadocodigo.domain.estado.controller;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.domain.estado.dto.NovoEstadoRequest;
import br.com.casadocodigo.domain.estado.repository.EstadoRepository;

/*
 * 2 (max 7)
 */
@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	//1
	private EstadoRepository repository;
	
	private EntityManager entityManager;

	public EstadoController(EstadoRepository repository, EntityManager entityManager) {
		this.repository = repository;
		this.entityManager = entityManager;
	}
	
	//1
	@PostMapping
	public ResponseEntity<Void> salvaEstado(@Valid @RequestBody NovoEstadoRequest request){
		repository.save(request.toModel(entityManager));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaEstado(@PathVariable Long id){
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
