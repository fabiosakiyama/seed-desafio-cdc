package br.com.casadocodigo.domain.livro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.casadocodigo.domain.autor.model.Autor;
import br.com.casadocodigo.domain.categoria.model.Categoria;
import br.com.casadocodigo.domain.livro.model.Livro;
import br.com.casadocodigo.util.dto.validators.ExistsId;
import br.com.casadocodigo.util.dto.validators.UniqueValue;
import lombok.Data;

/*
 * 5 (max 9)
 */
@Data
public class NovoLivroRequest {
	
	@NotBlank
	//1
	@UniqueValue(tableName = "Livro", columnName = "titulo")
	private String titulo;
	
	@NotBlank
	@Size(max = 500)
	private String resumo;
	
	private String sumario;

	@Min(value = 20)
	@NotNull
	private BigDecimal preco;

	@Min(value = 100)
	private long numeroDePaginas;

	@NotBlank
	@UniqueValue(tableName = "Livro", columnName = "isbn")
	private String isbn;
	
	@Future
	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataDePublicacao;
	
	@NotNull
	//1
	@ExistsId(klass = Categoria.class)
	private Long categoriaId;
	
	@NotNull
	@ExistsId(klass = Autor.class)
	private Long autorId;

	//3
	public Livro toModel(EntityManager entityManager) {
		Categoria categoria = entityManager.find(Categoria.class, categoriaId);
		Autor autor = entityManager.find(Autor.class, autorId);
		return new Livro(titulo, resumo, sumario, preco, numeroDePaginas, isbn, dataDePublicacao, categoria, autor);
	}

}
