package br.com.casadocodigo.domain.compra.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.casadocodigo.domain.compra.model.Compra;
import br.com.casadocodigo.domain.compra.model.ItemDeCompra;
import br.com.casadocodigo.domain.livro.model.Livro;
import br.com.casadocodigo.util.dto.validators.ExistsId;
import lombok.Data;

@Data
public class NovaCompraItemRequest {
	
	@NotNull
	@ExistsId(klass = Livro.class)
	private Long livroId;
	
	@Positive
	private long quantidade;
	
	public ItemDeCompra toModel(EntityManager entityManager, Compra compra) {
		Livro livro = entityManager.find(Livro.class, livroId);
		return new ItemDeCompra(livro, quantidade, compra);
		
	}
}