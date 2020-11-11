package br.com.casadocodigo.livro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casadocodigo.livro.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
