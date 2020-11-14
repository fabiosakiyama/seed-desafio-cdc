package br.com.casadocodigo.estado.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import br.com.casadocodigo.estado.model.Estado;
import br.com.casadocodigo.pais.model.Pais;
import br.com.casadocodigo.util.dto.validators.ExistsId;
import br.com.casadocodigo.util.dto.validators.UniqueValue;
import lombok.Data;

@Data
public class NovoEstadoRequest {
	
	@NotBlank
	@UniqueValue(tableName = "Estado", columnName = "nome")
	private String nome;
	
	@ExistsId(klass = Pais.class)
	private Long paisId;

	public Estado toModel(EntityManager entityManager) {
		return new Estado(nome, entityManager.find(Pais.class, paisId));
	}

}
