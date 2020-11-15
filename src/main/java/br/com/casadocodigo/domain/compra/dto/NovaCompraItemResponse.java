package br.com.casadocodigo.domain.compra.dto;

import lombok.Data;

@Data
public class NovaCompraItemResponse {

	private String livro;
	
	private long quantidade;

	public NovaCompraItemResponse(String livro, long quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
	}
}
