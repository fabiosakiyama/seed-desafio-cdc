package br.com.casadocodigo.livro.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.livro.dto.LivroResponse;
import br.com.casadocodigo.livro.dto.NovoLivroRequest;
import br.com.casadocodigo.livro.model.Livro;
import br.com.casadocodigo.livro.repository.LivroRepository;


/*
 * 6 (max 7)
 */
@RestController
@RequestMapping("/livros")
public class LivroController {
	
	private EntityManager entityManager;

	//1
	private LivroRepository repository;
	
	public LivroController(EntityManager entityManager, LivroRepository repository) {
		this.entityManager = entityManager;
		this.repository = repository;
	}

	//2
	@PostMapping
	@Transactional
	public ResponseEntity<Livro> salvaLivro(@Valid @RequestBody NovoLivroRequest request) {
		Livro livro = request.toModel(entityManager);
		entityManager.persist(livro);
		return ResponseEntity.ok(livro);
	}
	
	//2 
	@GetMapping
	public ResponseEntity<List<LivroResponse>> buscaTodosLivros(){
		List<LivroResponse> result = repository.findAll().stream().map(livro -> new LivroResponse(livro.getId(), livro.getTitulo())).collect(Collectors.toList());
		return ResponseEntity.ok(result);
	}
	
	//1
	@GetMapping("/{id}")
	public ResponseEntity<LivroResponse> buscaDetalheLivro(@PathVariable Long id) {
		Optional<Livro> possivelLivro = repository.findById(id);
		if(possivelLivro.isPresent()) {
			return ResponseEntity.ok(new LivroResponse(possivelLivro.get()));
		}
		return ResponseEntity.notFound().build();
	}
}
