package br.com.casadocodigo.domain.livro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casadocodigo.domain.livro.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
