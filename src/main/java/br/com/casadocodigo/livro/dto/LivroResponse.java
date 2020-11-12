package br.com.casadocodigo.livro.dto;

import br.com.casadocodigo.livro.model.Livro;
import lombok.Getter;

/*
 * 1 (max 9)
 */
@Getter
public class LivroResponse {

	private Long id;
	
	private String titulo;

	//1
	public LivroResponse(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
	}
}
