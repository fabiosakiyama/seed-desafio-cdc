package br.com.casadocodigo.categoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casadocodigo.categoria.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
