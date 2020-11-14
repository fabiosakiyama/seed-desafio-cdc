package br.com.casadocodigo.domain.pagamento.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.domain.pagamento.dto.NovoPagamentoRequest;
import br.com.casadocodigo.domain.pagamento.dto.PaisContemEstadosValidator;

/*
 * 2 (max 7)
 */
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	//1
	private PaisContemEstadosValidator paisContemEstadosValidator;
	
	public PagamentoController(PaisContemEstadosValidator paisContemEstadosValidator) {
		this.paisContemEstadosValidator = paisContemEstadosValidator;
	}

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(paisContemEstadosValidator);
	}
	
	//1
	@PostMapping
	public ResponseEntity<Void> recebeInformacoesDePagamento(@Valid @RequestBody NovoPagamentoRequest request){
		return ResponseEntity.ok().build();
	}

}
