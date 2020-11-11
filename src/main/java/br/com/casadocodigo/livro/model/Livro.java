package br.com.casadocodigo.livro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;

import br.com.casadocodigo.autor.model.Autor;
import br.com.casadocodigo.categoria.model.Categoria;
import lombok.Getter;

@Entity
@Getter
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String titulo;
	
	@Column(nullable = false, length = 500)
	private String resumo;
	
	@Lob
	@Column
	private String sumario;

	@Min(value = 20)
	@Column(nullable = false)
	private BigDecimal preco;

	@Min(value = 100)
	@Column(nullable = false)
	private long numeroDePaginas;

	@Lob
	@Column(nullable = false, unique = true)
	private String isbn;
	
	@Future
	@Column(nullable = false)
	private LocalDate dataDePublicacao;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "autor_id")
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
