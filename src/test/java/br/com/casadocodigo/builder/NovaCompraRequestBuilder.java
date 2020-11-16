package br.com.casadocodigo.builder;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.apache.commons.lang3.RandomUtils.nextDouble;

import java.math.BigDecimal;

import br.com.casadocodigo.domain.compra.dto.NovaCompraRequest;

public class NovaCompraRequestBuilder {
	
	private NovaCompraRequest request;
	
	private NovaCompraRequestBuilder() {
		this.request = new NovaCompraRequest();
	}
	
	public static NovaCompraRequestBuilder builder() {
		NovaCompraRequestBuilder builder = new NovaCompraRequestBuilder();
		return builder;
	}
	
	public NovaCompraRequestBuilder aleatorio() {
		this.request.setEmail(randomAlphanumeric(10) + "@" + randomAlphanumeric(4) + "." + randomAlphanumeric(4));
		this.request.setNome(randomAlphanumeric(10));
		this.request.setSobreNome(randomAlphanumeric(10));
		this.request.setDocumento(randomAlphanumeric(3) + "." + randomAlphanumeric(3) + "." + randomAlphanumeric(3) + "-" + randomAlphanumeric(2));
		this.request.setEndereco(randomAlphanumeric(30));
		this.request.setComplemento(randomAlphanumeric(20));
		this.request.setCidade(randomAlphanumeric(20));
		this.request.setPaisId(nextLong());
		this.request.setTelefone(randomAlphanumeric(20));
		this.request.setCep(randomAlphanumeric(20));
		this.request.setTotal(new BigDecimal(nextDouble()));
		this.request.getItens().add(NovaCompraItemRequestBuilder.builder().aleatorio().build());
	    return this;
	}
	
	public NovaCompraRequest build() {
		return this.request;
	}
}
