package br.com.casadocodigo.domain.categoria.dto;

import javax.validation.constraints.NotBlank;

import br.com.casadocodigo.domain.categoria.model.Categoria;
import br.com.casadocodigo.util.dto.validators.UniqueValue;
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
