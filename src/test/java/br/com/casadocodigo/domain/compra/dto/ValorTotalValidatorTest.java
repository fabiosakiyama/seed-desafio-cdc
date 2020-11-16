package br.com.casadocodigo.domain.compra.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import br.com.casadocodigo.builder.NovaCompraRequestBuilder;
import br.com.casadocodigo.domain.livro.repository.LivroRepository;

public class ValorTotalValidatorTest {

	@Test
	@DisplayName("Compra com valor total igual ao valor da soma dos preços dos livros, não gera erro")
	public void teste1() {
		NovaCompraRequest request = NovaCompraRequestBuilder.builder().aleatorio().build();
		LivroRepository livroRepository = Mockito.mock(LivroRepository.class);
		Errors errors = new BeanPropertyBindingResult(request, "request");
		ValorTotalValidator valorTotalValidator = new ValorTotalValidator(livroRepository);
		List<Long> ids = request.getItens().stream().map(NovaCompraItemRequest::getLivroId).collect(Collectors.toList());
		Mockito.when(livroRepository.somaValorTotalPorIds(ids)).thenReturn(request.getTotal());
		valorTotalValidator.validate(request, errors);
		assertTrue(errors.getErrorCount() == 0);
	}
	
	@Test
	@DisplayName("Compra com valor total diferente do valor da soma dos preços dos livros, gera erro")
	public void teste2() {
		NovaCompraRequest request = NovaCompraRequestBuilder.builder().aleatorio().build();
		LivroRepository livroRepository = Mockito.mock(LivroRepository.class);
		Errors errors = new BeanPropertyBindingResult(request, "request");
		ValorTotalValidator valorTotalValidator = new ValorTotalValidator(livroRepository);
		List<Long> ids = request.getItens().stream().map(NovaCompraItemRequest::getLivroId).collect(Collectors.toList());
		Mockito.when(livroRepository.somaValorTotalPorIds(ids)).thenReturn(request.getTotal().add(BigDecimal.ONE));
		valorTotalValidator.validate(request, errors);
		assertTrue(errors.getErrorCount() == 1);
	}
}
