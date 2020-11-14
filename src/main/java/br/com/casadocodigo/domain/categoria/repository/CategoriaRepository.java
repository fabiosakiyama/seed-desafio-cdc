package br.com.casadocodigo.domain.categoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casadocodigo.domain.categoria.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
