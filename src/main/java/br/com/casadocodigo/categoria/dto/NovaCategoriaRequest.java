package br.com.casadocodigo.categoria.dto;

import javax.validation.constraints.NotBlank;

import br.com.casadocodigo.categoria.model.Categoria;
import br.com.casadocodigo.util.UniqueValue;
import lombok.Data;

@Data
public class NovaCategoriaRequest {
	
	@NotBlank
	@UniqueValue(tableName = "Categoria", columnName  = "nome")
	private String nome;

	public Categoria toModel() {
		return new Categoria(nome);
	}
}
