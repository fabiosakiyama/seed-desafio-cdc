package br.com.casadocodigo.domain.autor.dto;

import java.time.Instant;

import br.com.casadocodigo.domain.autor.model.Autor;
import lombok.Getter;

@Getter
public class AutorResponse {

	private Long id;

	private String nome;

	private String email;

	private String descricao;

	private Instant instanteRegistrado;
	
	public AutorResponse(Autor autor) {
		this.id = autor.getId();
		this.nome = autor.getNome();
		this.email = autor.getEmail();
		this.descricao = autor.getDescricao();
		this.instanteRegistrado = autor.getInstanteRegistrado();
	}
}
