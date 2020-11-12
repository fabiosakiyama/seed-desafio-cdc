package br.com.casadocodigo.livro.dto;

import br.com.casadocodigo.livro.model.Livro;
import lombok.Getter;

@Getter
public class LivroResponse {

	private Long id;
	
	private String titulo;

	public LivroResponse(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
	}
}
