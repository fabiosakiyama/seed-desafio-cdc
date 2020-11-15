package br.com.casadocodigo.domain.compra.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import br.com.casadocodigo.domain.compra.model.Compra;
import br.com.casadocodigo.domain.compra.model.ItemDeCompra;
import br.com.casadocodigo.domain.estado.model.Estado;
import br.com.casadocodigo.domain.pais.model.Pais;
import br.com.casadocodigo.util.RegexUtils;
import br.com.casadocodigo.util.dto.validators.ExistsId;
import lombok.Data;

/*
 * 8 (max 9)
 */
@Data
public class NovaCompraRequest {
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobreNome;
	
	@NotBlank
	@Pattern(regexp = RegexUtils.CPF_CNPJ, message = "{valid.CPFCNPJ}")
	private String documento;
	
	@NotBlank
	private String endereco;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String cidade;
	
	//1
	@NotNull
	@ExistsId(klass = Pais.class)
	private Long paisId;
	
	private Long estadoId;
	
	@NotBlank
	private String telefone;
	
	@NotBlank
	private String cep;
	
	@NotNull
	@Positive
	private BigDecimal total;
	
	//1
	@NotNull
	@NotEmpty
	private List<@Valid NovaCompraItemRequest> itens;

	//6
	public Compra toModel(EntityManager entityManager) {
		Pais pais = entityManager.find(Pais.class, paisId);
		Estado estado = null;
		if(estadoId != null) {
			estado = entityManager.find(Estado.class, estadoId);
		}
		
		Compra compra = new Compra(this.email, this.nome, this.sobreNome, 
				this.documento, this.endereco, this.complemento, this.cidade, pais, estado, this.telefone, this.cep);

		List<ItemDeCompra> itensCompra = itens.stream().map(i -> i.toModel(entityManager, compra)).collect(Collectors.toList());
		compra.getItens().addAll(itensCompra);
		return compra;
	}
}
