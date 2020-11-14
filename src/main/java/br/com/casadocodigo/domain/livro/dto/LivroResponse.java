package br.com.casadocodigo.domain.livro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.casadocodigo.domain.autor.dto.AutorResponse;
import br.com.casadocodigo.domain.categoria.dto.CategoriaResponse;
import br.com.casadocodigo.domain.livro.model.Livro;
import lombok.Getter;

/*
 * 3(max 9)
 */
@Getter
@JsonInclude(Include.NON_NULL)
public class LivroResponse {

	private Long id;
	
	private String titulo;
	
	private String resumo;
	
	private String sumario;

	private BigDecimal preco;

	private Long numeroDePaginas;
	
	private String isbn;
	
	private LocalDate dataDePublicacao;
	
	//1
	private CategoriaResponse categoria;
	
	//1
	private AutorResponse autor;

	//1
	public LivroResponse(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.preco = livro.getPreco();
		this.numeroDePaginas = livro.getNumeroDePaginas();
		this.isbn = livro.getIsbn();
		this.dataDePublicacao = livro.getDataDePublicacao();
		this.categoria = new CategoriaResponse(livro.getCategoria());
		this.autor = new AutorResponse(livro.getAutor());
	}

	public LivroResponse(Long id, String titulo) {
		this.id = id;
		this.titulo = titulo;
	}
}
