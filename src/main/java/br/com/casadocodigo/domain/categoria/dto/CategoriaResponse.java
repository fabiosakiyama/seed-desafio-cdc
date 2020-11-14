package br.com.casadocodigo.domain.categoria.dto;

import br.com.casadocodigo.domain.categoria.model.Categoria;
import lombok.Getter;

@Getter
public class CategoriaResponse {

	private Long id;

	private String nome;

	public CategoriaResponse(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

}
