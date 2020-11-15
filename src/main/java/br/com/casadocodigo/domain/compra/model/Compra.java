package br.com.casadocodigo.domain.compra.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.casadocodigo.domain.estado.model.Estado;
import br.com.casadocodigo.domain.pais.model.Pais;
import br.com.casadocodigo.util.RegexUtils;
import lombok.Getter;

@Entity
@Getter
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotNull
	@Column
	private String email;
	
	@NotNull
	@Column
	private String nome;
	
	@NotNull
	@Column
	private String sobreNome;
	
	@NotNull
	@Column
	@Pattern(regexp = RegexUtils.CPF_CNPJ, message = "{valid.CPFCNPJ}")
	private String documento;
	
	@NotNull
	@Column
	private String endereco;
	
	@NotNull
	@Column
	private String complemento;
	
	@NotNull
	@Column
	private String cidade;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Pais pais;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Estado estado;
	
	@NotNull
	@Column
	private String telefone;
	
	@NotNull
	@Column
	private String cep;
	
	@OneToMany(mappedBy = "compra", orphanRemoval = true)
	private List<ItemDeCompra> itens = new ArrayList<>();

	@Deprecated
	public Compra() {}

	public Compra(String email, String nome, String sobreNome, String documento, String endereco, String complemento,
			String cidade, Pais pais, Estado estado, String telefone, String cep) {
		this.email = email;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.pais = pais;
		this.estado = estado;
		this.telefone = telefone;
		this.cep = cep;
	}

	@Transient
	public BigDecimal getTotal() {
		return itens.stream().map(i -> i.getLivro().getPreco().multiply(new BigDecimal(i.getQuantidade()))).reduce(BigDecimal::add).get();
	}
}
