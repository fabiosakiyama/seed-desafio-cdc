package br.com.casadocodigo.domain.compra.model;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.casadocodigo.domain.cupom.model.Cupom;
import br.com.casadocodigo.domain.estado.model.Estado;
import br.com.casadocodigo.domain.estado.model.EstadoBuilder;

public class CompraTest {

	@Test
	@DisplayName("Adiciona cupom válido em uma compra nova, não gera erro")
	public void teste1() {
		Estado estado = EstadoBuilder.builder().aleatorio().build();
		Compra compra = new Compra(randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				estado.getPais(), estado, randomAlphanumeric(15), randomAlphanumeric(15));
		Cupom cupom = new Cupom(randomAlphanumeric(15), nextInt(), LocalDate.now());
		compra.adicionaCupom(cupom);
	}

	@Test
	@DisplayName("Adiciona cupom inválido (null) em uma compra nova, gera erro")
	public void teste2() {
		Estado estado = EstadoBuilder.builder().aleatorio().build();
		Compra compra = new Compra(randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				estado.getPais(), estado, randomAlphanumeric(15), randomAlphanumeric(15));
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			compra.adicionaCupom(null);
		});
	}

	@Test
	@DisplayName("Adiciona um cupom válido em uma compra nova, e tenta adicionar um novo cupom na mesma compra, gera erro")
	public void teste3() {
		Estado estado = EstadoBuilder.builder().aleatorio().build();
		Compra compra = new Compra(randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				estado.getPais(), estado, randomAlphanumeric(15), randomAlphanumeric(15));
		Cupom cupom = new Cupom(randomAlphanumeric(15), nextInt(), LocalDate.now().plusDays(1));
		compra.adicionaCupom(cupom);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			compra.adicionaCupom(cupom);
		});

	}

	@Test
	@DisplayName("Adiciona um cupom válido porém em uma compra já existente, gera erro.")
	public void teste5() {
		Estado estado = EstadoBuilder.builder().aleatorio().build();
		Compra compra = new Compra(randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				estado.getPais(), estado, randomAlphanumeric(15), randomAlphanumeric(15));
		compra.setId(nextLong());
		Cupom cupom = new Cupom(randomAlphanumeric(15), nextInt(), LocalDate.now().plusDays(1));
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			compra.adicionaCupom(cupom);
		});

	}

	@Test
	@DisplayName("Adiciona um cupom expirado, gera erro")
	public void teste6() {
		Estado estado = EstadoBuilder.builder().aleatorio().build();
		Compra compra = new Compra(randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				estado.getPais(), estado, randomAlphanumeric(15), randomAlphanumeric(15));
		compra.setId(nextLong());
		Cupom cupom = new Cupom(randomAlphanumeric(15), nextInt(), LocalDate.now().minusDays(1));
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			compra.adicionaCupom(cupom);
		});
	}
	
	@Test
	@DisplayName("Valor total e valor total com o desconto devem ser iguais se não existir cupom")
	public void teste7() {
		Estado estado = EstadoBuilder.builder().aleatorio().build();
		Compra compra = new Compra(randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				estado.getPais(), estado, randomAlphanumeric(15), randomAlphanumeric(15));
		ItemDeCompra itemDeCompra = Mockito.mock(ItemDeCompra.class);
		Mockito.when(itemDeCompra.getValor()).thenReturn(BigDecimal.TEN);
		compra.adicionaItens(Arrays.asList(itemDeCompra));
		assertTrue(compra.getTotal().compareTo(compra.getTotalComDesconto()) == 0);
	}
	
	@Test
	@DisplayName("Valor total e valor total com o desconto devem ser diferentes se existir cupom")
	public void teste8() {
		Estado estado = EstadoBuilder.builder().aleatorio().build();
		Compra compra = new Compra(randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15), randomAlphanumeric(15),
				estado.getPais(), estado, randomAlphanumeric(15), randomAlphanumeric(15));
		Cupom cupom = new Cupom(randomAlphanumeric(15), nextInt(), LocalDate.now());
		ItemDeCompra itemDeCompra = Mockito.mock(ItemDeCompra.class);
		Mockito.when(itemDeCompra.getValor()).thenReturn(BigDecimal.TEN);
		compra.adicionaItens(Arrays.asList(itemDeCompra));
		compra.adicionaCupom(cupom);
		assertFalse(compra.getTotal().compareTo(compra.getTotalComDesconto()) == 0);
	}
}
