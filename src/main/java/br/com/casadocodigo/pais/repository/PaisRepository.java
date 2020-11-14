package br.com.casadocodigo.pais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.pais.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long>{

}
