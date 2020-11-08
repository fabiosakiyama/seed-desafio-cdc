package br.com.casadocodigo.categoria.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.categoria.dto.NovaCategoriaRequest;
import br.com.casadocodigo.categoria.model.Categoria;
import br.com.casadocodigo.categoria.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	private CategoriaRepository categoriaRepository;
	
	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	public ResponseEntity<Categoria> salvaCategoria(@Valid @RequestBody NovaCategoriaRequest request) {
		Categoria categoria = categoriaRepository.save(request.toModel());
		return ResponseEntity.ok(categoria);
	}
}
