package br.com.casadocodigo.builder;

import static org.apache.commons.lang3.RandomUtils.nextLong;

import br.com.casadocodigo.domain.compra.dto.NovaCompraItemRequest;

public class NovaCompraItemRequestBuilder {

	private NovaCompraItemRequest request;

	private NovaCompraItemRequestBuilder() {
		this.request = new NovaCompraItemRequest();
	}

	public static NovaCompraItemRequestBuilder builder() {
		NovaCompraItemRequestBuilder builder = new NovaCompraItemRequestBuilder();
		return builder;
	}

	public NovaCompraItemRequestBuilder aleatorio() {
		this.request.setLivroId(nextLong());
		this.request.setQuantidade(nextLong());
		return this;
	}

	public NovaCompraItemRequest build() {
		return this.request;
	}
}
