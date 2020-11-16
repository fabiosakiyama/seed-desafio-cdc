package br.com.casadocodigo.domain.compra.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.springframework.util.Assert;

import br.com.casadocodigo.domain.cupom.model.Cupom;
import br.com.casadocodigo.domain.estado.model.Estado;
import br.com.casadocodigo.domain.pais.model.Pais;
import br.com.casadocodigo.util.RegexUtils;
import lombok.Getter;

/*
 * 6 (max 9)
 */
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
	
	//1
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Pais pais;
	
	//1
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Estado estado;
	
	@NotNull
	@Column
	private String telefone;
	
	@NotNull
	@Column
	private String cep;
	
	//1
	@OneToMany(mappedBy = "compra", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<ItemDeCompra> itens = new ArrayList<>();
	
	//1
	@ManyToOne(fetch = FetchType.LAZY)
	private Cupom cupom;

	@Deprecated
	public Compra() {}
	
	void setId(Long id) {
		this.id = id;
	}

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
		//1
		BigDecimal valorTotal = itens.stream().map(ItemDeCompra::getValor).reduce(BigDecimal::add).get();
		return valorTotal;
	}
	
	@Transient
	public BigDecimal getTotalComDesconto() {
		BigDecimal valorTotal = this.getTotal();
		
		//1
		if(cupom != null) {
			BigDecimal valorDoDesconto = valorTotal.multiply(new BigDecimal(cupom.getPercentualDesconto())).divide(new BigDecimal("100"));
			valorTotal = valorTotal.subtract(valorDoDesconto);
		}
		
		return valorTotal;
	}

	public void adicionaCupom(Cupom cupom) {
		Assert.notNull(cupom, "invalid.coupon");
		Assert.isNull(this.cupom, "order.must.not.have.coupon");
		Assert.isNull(this.id, "order.must.be.new");
		Assert.isTrue(!LocalDate.now().isAfter(cupom.getValidadeDoCupom()), "coupon.expired");
		this.cupom = cupom;
	}

	public void adicionaItens(List<ItemDeCompra> itens) {
		this.itens.addAll(itens);
	}
}
