package br.com.casadocodigo.pais.dto;

import br.com.casadocodigo.pais.model.Pais;
import lombok.Getter;

@Getter
public class PaisResponse {
	
	private String nome;

	public PaisResponse(Pais pais) {
		this.nome = pais.getNome();
	}
	
}
