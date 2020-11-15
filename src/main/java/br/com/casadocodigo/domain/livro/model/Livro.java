package br.com.casadocodigo.domain.livro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.casadocodigo.domain.autor.model.Autor;
import br.com.casadocodigo.domain.categoria.model.Categoria;
import lombok.Getter;

@Entity
@Getter
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true)
	private String titulo;
	
	@NotNull
	@Column(length = 500)
	private String resumo;
	
	@Lob
	@Column
	private String sumario;

	@Min(value = 20)
	@NotNull
	@Column
	private BigDecimal preco;

	@Min(value = 100)
	@NotNull
	@Column
	private long numeroDePaginas;

	@Lob
	@NotNull
	@Column(unique = true)
	private String isbn;
	
	@Future
	@NotNull
	@Column
	private LocalDate dataDePublicacao;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Categoria categoria;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Autor autor;
	
	@Deprecated
	public Livro() {}

	public Livro(String titulo, String resumo, String sumario, BigDecimal preco,
			long numeroDePaginas, String isbn, LocalDate dataDePublicacao, Categoria categoria,
			Autor autor) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroDePaginas = numeroDePaginas;
		this.isbn = isbn;
		this.dataDePublicacao = dataDePublicacao;
		this.categoria = categoria;
		this.autor = autor;
	}
}
