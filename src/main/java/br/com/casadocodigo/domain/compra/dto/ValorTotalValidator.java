package br.com.casadocodigo.domain.compra.dto;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.casadocodigo.domain.livro.repository.LivroRepository;

/*
 * 4 (max 9)
 */
@Component
public class ValorTotalValidator implements Validator {
	
	//1
	private LivroRepository livroRepository;

	public ValorTotalValidator(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	// 1
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(NovaCompraRequest.class);
	}

	//2
	@Override
	public void validate(Object target, Errors errors) {
		NovaCompraRequest request = (NovaCompraRequest) target;
		BigDecimal somaDosLivros = livroRepository.somaValorTotalPorIds(request.getItens().stream().map(NovaCompraItemRequest::getLivroId).collect(Collectors.toList()));
		BigDecimal somaDaRequest = request.getTotal();
		if(somaDosLivros.compareTo(somaDaRequest) != 0) {
			String args[] = new String[] {somaDaRequest.toString(), somaDosLivros.toString()};
			errors.rejectValue("total", "total.value.does.not.match", args, "total.value.does.not.match");
		}
	}
}
