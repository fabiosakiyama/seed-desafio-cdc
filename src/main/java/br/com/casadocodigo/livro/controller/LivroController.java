package br.com.casadocodigo.livro.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.livro.dto.NovoLivroRequest;
import br.com.casadocodigo.livro.model.Livro;

@RestController
@RequestMapping("/livros")
public class LivroController {
	
	private EntityManager entityManager;

	public LivroController(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Livro> salvaLivro(@Valid @RequestBody NovoLivroRequest request) {
		Livro livro = request.toModel(entityManager);
		entityManager.persist(livro);
		return ResponseEntity.ok(livro);
	}

}
