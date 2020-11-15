package br.com.casadocodigo.domain.compra.controller;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.domain.compra.dto.NovaCompraRequest;
import br.com.casadocodigo.domain.compra.dto.NovaCompraResponse;
import br.com.casadocodigo.domain.compra.dto.PaisContemEstadosValidator;
import br.com.casadocodigo.domain.compra.dto.ValorTotalValidator;
import br.com.casadocodigo.domain.compra.model.Compra;
import br.com.casadocodigo.domain.compra.repository.CompraRepository;

/*
 * 5 (max 7)
 */
@RestController
@RequestMapping("/compras")
public class CompraController {
	
	//1
	private PaisContemEstadosValidator paisContemEstadosValidator;
	
	//1
	private ValorTotalValidator valorTotalValidator;
	
	//1
	private CompraRepository compraRepository;
	
	private EntityManager entityManager;
	
	public CompraController(PaisContemEstadosValidator paisContemEstadosValidator, ValorTotalValidator valorTotalValidator, CompraRepository compraRepository, EntityManager entityManager) {
		this.paisContemEstadosValidator = paisContemEstadosValidator;
		this.valorTotalValidator = valorTotalValidator;
		this.compraRepository = compraRepository;
		this.entityManager = entityManager;
	}

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(paisContemEstadosValidator, valorTotalValidator);
	}
	
	//2
	@PostMapping
	@Transactional
	public ResponseEntity<NovaCompraResponse> realizaCompra(@Valid @RequestBody NovaCompraRequest request){
		NovaCompraResponse novaCompraResponse = new NovaCompraResponse(compraRepository.save(request.toModel(entityManager)));
		return ResponseEntity.status(HttpStatus.CREATED).body(novaCompraResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NovaCompraResponse> buscaDetalhesDaCompra(@PathVariable Long id){
			Optional<Compra> possivelCompra = compraRepository.findById(id);
			if(possivelCompra.isPresent()) {
				NovaCompraResponse novaCompraResponse = new NovaCompraResponse(possivelCompra.get());
				return ResponseEntity.ok(novaCompraResponse);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	
	

}
