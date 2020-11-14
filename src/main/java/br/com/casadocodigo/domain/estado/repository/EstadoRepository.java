package br.com.casadocodigo.domain.estado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.domain.estado.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
