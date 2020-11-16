package br.com.casadocodigo.domain.compra.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import br.com.casadocodigo.domain.cupom.model.Cupom;
import br.com.casadocodigo.domain.estado.model.Estado;
import br.com.casadocodigo.domain.pais.model.Pais;
import br.com.casadocodigo.util.RegexUtils;
import br.com.casadocodigo.util.dto.validators.ExistsId;
import lombok.Data;

/*
 * 9 (max 9)
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
	
	@ExistsId(klass = Estado.class, isOptional = true)
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
	private List<@Valid NovaCompraItemRequest> itens = new ArrayList<>();
	
	//1
	@ExistsId(klass = Cupom.class, isOptional = true)
	private Long cupomId;

	//1
	public Compra toModel(EntityManager entityManager) {
		//1
		Pais pais = entityManager.find(Pais.class, paisId);
		//1
		Estado estado = null;
		//1
		if(estadoId != null) {
			estado = entityManager.find(Estado.class, estadoId);
		}
		Compra compra = new Compra(this.email, this.nome, this.sobreNome, 
				this.documento, this.endereco, this.complemento, this.cidade, pais, estado, this.telefone, this.cep);
		//1
		compra.adicionaItens(itens.stream().map(i -> i.toModel(entityManager, compra)).collect(Collectors.toList()));
		
		Cupom cupom = null;
		//1
		if(cupomId != null) {
			cupom = entityManager.find(Cupom.class, cupomId);
			compra.adicionaCupom(cupom);
		}
		return compra;
	}
}
