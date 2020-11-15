package br.com.casadocodigo.domain.livro.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.casadocodigo.domain.livro.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
	
	@Query("SELECT sum(preco) FROM Livro WHERE id IN (:ids)")
	BigDecimal somaValorTotalPorIds(@Param("ids") List<Long> ids);

}
