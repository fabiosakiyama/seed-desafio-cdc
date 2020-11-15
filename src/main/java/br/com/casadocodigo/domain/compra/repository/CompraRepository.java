package br.com.casadocodigo.domain.compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.domain.compra.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

}
