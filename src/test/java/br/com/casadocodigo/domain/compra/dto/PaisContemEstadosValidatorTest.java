package br.com.casadocodigo.domain.compra.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import br.com.casadocodigo.builder.NovaCompraRequestBuilder;
import br.com.casadocodigo.domain.estado.model.Estado;
import br.com.casadocodigo.domain.estado.model.EstadoBuilder;
import br.com.casadocodigo.domain.pais.model.Pais;
import br.com.casadocodigo.domain.pais.repository.PaisRepository;

public class PaisContemEstadosValidatorTest {
	

	@DisplayName("Compra com país inválido, gera erro")
	@ParameterizedTest
	@MethodSource("br.com.casadocodigo.domain.compra.dto.PaisContemEstadosValidatorTest#defaults")
	public void teste1(NovaCompraRequest request) {
		PaisRepository paisRepository = Mockito.mock(PaisRepository.class);
		Errors errors = new BeanPropertyBindingResult(request, "request");
		PaisContemEstadosValidator paisContemEstadosValidator = new PaisContemEstadosValidator(paisRepository);
		Pais paisSemEstados = new Pais("NomePais");
		assertTrue(paisSemEstados.getEstados().isEmpty());
		Mockito.when(paisRepository.findById(request.getPaisId())).thenReturn(Optional.empty());
		paisContemEstadosValidator.validate(request, errors);
		assertTrue(errors.getErrorCount() == 1);
	}
	
	@DisplayName("Compra com país válido, sem estados e sem estadoId, não gera erro")
	@ParameterizedTest
	@MethodSource("br.com.casadocodigo.domain.compra.dto.PaisContemEstadosValidatorTest#defaults")
	public void teste2(NovaCompraRequest request) {
		request.setEstadoId(null);
		PaisRepository paisRepository = Mockito.mock(PaisRepository.class);
		Errors errors = new BeanPropertyBindingResult(request, "request");
		PaisContemEstadosValidator paisContemEstadosValidator = new PaisContemEstadosValidator(paisRepository);
		Pais paisSemEstados = new Pais("NomePais");
		assertTrue(paisSemEstados.getEstados().isEmpty());
		Mockito.when(paisRepository.findById(request.getPaisId())).thenReturn(Optional.of(paisSemEstados));
		paisContemEstadosValidator.validate(request, errors);
		assertTrue(errors.getErrorCount() == 0);
	}
	
	@DisplayName("Compra com país válido, sem estados e com estadoId, gera erro")
	@ParameterizedTest
	@MethodSource("br.com.casadocodigo.domain.compra.dto.PaisContemEstadosValidatorTest#defaults")
	public void teste3(NovaCompraRequest request) {
		request.setEstadoId(nextLong());
		PaisRepository paisRepository = Mockito.mock(PaisRepository.class);
		Errors errors = new BeanPropertyBindingResult(request, "request");
		PaisContemEstadosValidator paisContemEstadosValidator = new PaisContemEstadosValidator(paisRepository);
		Pais paisSemEstados = new Pais("NomePais");
		assertTrue(paisSemEstados.getEstados().isEmpty());
		Mockito.when(paisRepository.findById(request.getPaisId())).thenReturn(Optional.of(paisSemEstados));
		paisContemEstadosValidator.validate(request, errors);
		assertTrue(errors.getErrorCount() == 1);
	}
	
	@DisplayName("Compra com país válido, com estados, e com estadoId fazendo match, não gera erro")
	@ParameterizedTest
	@MethodSource("br.com.casadocodigo.domain.compra.dto.PaisContemEstadosValidatorTest#defaults")
	public void teste4(NovaCompraRequest request) {
		PaisRepository paisRepository = Mockito.mock(PaisRepository.class);
		Errors errors = new BeanPropertyBindingResult(request, "request");
		Estado estado = EstadoBuilder.builder().aleatorio().build();
		estado.getPais().getEstados().add(estado);
		PaisContemEstadosValidator paisContemEstadosValidator = new PaisContemEstadosValidator(paisRepository);
		request.setEstadoId(estado.getId());
		Mockito.when(paisRepository.findById(request.getPaisId())).thenReturn(Optional.of(estado.getPais()));
		paisContemEstadosValidator.validate(request, errors);
		assertTrue(errors.getErrorCount() == 0);
	}
	
	@DisplayName("Compra com país válido, com estados, e com estadoId não fazendo match, gera erro")
	@ParameterizedTest
	@MethodSource("br.com.casadocodigo.domain.compra.dto.PaisContemEstadosValidatorTest#defaults")
	public void teste5(NovaCompraRequest request) {
		PaisRepository paisRepository = Mockito.mock(PaisRepository.class);
		Errors errors = new BeanPropertyBindingResult(request, "request");
		Estado estado = EstadoBuilder.builder().aleatorio().build();
		PaisContemEstadosValidator paisContemEstadosValidator = new PaisContemEstadosValidator(paisRepository);
		request.setEstadoId(estado.getId() + 1L);
		Mockito.when(paisRepository.findById(request.getPaisId())).thenReturn(Optional.of(estado.getPais()));
		paisContemEstadosValidator.validate(request, errors);
		assertTrue(errors.getErrorCount() == 1);
	}
	
	@SuppressWarnings("unused")
	private static Stream<Arguments> defaults() {
	    return Stream.of(
	      Arguments.of(NovaCompraRequestBuilder.builder().aleatorio().build(), NovaCompraRequestBuilder.builder().aleatorio().build())
	    );
	}

}
