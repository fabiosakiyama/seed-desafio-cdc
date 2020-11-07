package br.com.casadocodigo.autor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casadocodigo.autor.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
