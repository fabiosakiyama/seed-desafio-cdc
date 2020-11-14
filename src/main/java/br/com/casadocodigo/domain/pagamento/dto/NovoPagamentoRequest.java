package br.com.casadocodigo.domain.pagamento.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.casadocodigo.domain.pais.model.Pais;
import br.com.casadocodigo.util.dto.validators.ExistsId;
import lombok.Data;

@Data
public class NovoPagamentoRequest {
	
	private static final String CPF_CNPJ_REGEX = "(^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$)";
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobreNome;
	
	@NotBlank
	@Pattern(regexp =  CPF_CNPJ_REGEX)
	private String documento;
	
	@NotBlank
	private String endereco;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String cidade;
	
	@NotNull
	@ExistsId(klass = Pais.class)
	private Long paisId;
	
	private Long estadoId;
	
	@NotBlank
	private String telefone;
	
	@NotBlank
	private String cep;

}
