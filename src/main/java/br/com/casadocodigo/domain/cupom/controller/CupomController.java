package br.com.casadocodigo.domain.cupom.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.domain.cupom.dto.NovoCupomRequest;
import br.com.casadocodigo.domain.cupom.repository.CupomRepository;

/*
 * 2 (max 7)
 */
@RestController
@RequestMapping("/cupons")
public class CupomController {
	
	//1
	private CupomRepository cupomRepository;
	
	public CupomController(CupomRepository cupomRepository) {
		this.cupomRepository = cupomRepository;
	}

	//1
	@PostMapping
	public ResponseEntity<Void> salvaCupom(@Valid @RequestBody NovoCupomRequest request) {
		cupomRepository.save(request.toModel());
		return ResponseEntity.ok().build();
	}
}
