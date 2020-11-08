package br.com.casadocodigo.autor.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.casadocodigo.autor.model.Autor;
import br.com.casadocodigo.util.UniqueValue;
import lombok.Data;

@Data
public class NovoAutorRequest {
	
	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	@UniqueValue(tableName = "Autor", columnName = "email")
	private String email;
	
	@NotBlank
	@Size(max = 400)
	private String descricao;

	public Autor toModel() {
		return new Autor(nome, email, descricao);
	}

}
