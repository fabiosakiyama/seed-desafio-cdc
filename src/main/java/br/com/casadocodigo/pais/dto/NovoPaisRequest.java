package br.com.casadocodigo.pais.dto;

import javax.validation.constraints.NotBlank;

import br.com.casadocodigo.pais.model.Pais;
import br.com.casadocodigo.util.dto.validators.UniqueValue;
import lombok.Data;

@Data
public class NovoPaisRequest {
	
	@NotBlank
	@UniqueValue(tableName = "Pais", columnName = "nome")
	private String nome;

	public Pais toModel() {
		return new Pais(this.nome);
	}
}
