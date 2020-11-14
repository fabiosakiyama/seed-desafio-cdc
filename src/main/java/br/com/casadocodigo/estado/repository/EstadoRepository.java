package br.com.casadocodigo.estado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.estado.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
