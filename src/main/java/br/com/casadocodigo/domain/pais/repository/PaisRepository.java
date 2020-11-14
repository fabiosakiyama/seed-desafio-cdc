package br.com.casadocodigo.domain.pais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.domain.pais.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long>{

}
