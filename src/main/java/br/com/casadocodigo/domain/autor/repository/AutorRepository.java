package br.com.casadocodigo.domain.autor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casadocodigo.domain.autor.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
	
	public Optional<Autor> findByEmail(String email);

}
