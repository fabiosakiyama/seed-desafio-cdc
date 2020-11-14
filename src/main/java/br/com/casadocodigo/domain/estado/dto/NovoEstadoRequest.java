package br.com.casadocodigo.domain.estado.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import br.com.casadocodigo.domain.estado.model.Estado;
import br.com.casadocodigo.domain.pais.model.Pais;
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
