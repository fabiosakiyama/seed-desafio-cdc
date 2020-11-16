package br.com.casadocodigo.domain.compra.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.casadocodigo.domain.livro.model.Livro;
import lombok.Getter;

@Entity
@Getter
public class ItemDeCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false) 
	private Livro livro;
	
	@NotNull
	@Column
	@Positive
	private long quantidade;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY,  optional = false)
	private Compra compra;

	@Deprecated
	public ItemDeCompra() {}

	public ItemDeCompra(Livro livro, long quantidade, Compra compra) {
		this.livro = livro;
		this.quantidade = quantidade;
		this.compra = compra;
	}
	
	@Transient
	public String getNomeLivro() {
		return this.livro.getTitulo();
	}
	
	@Transient
	public BigDecimal getValor() {
		return this.livro.getPreco().multiply(new BigDecimal(quantidade));
	}
}
