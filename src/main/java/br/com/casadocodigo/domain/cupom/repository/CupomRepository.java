package br.com.casadocodigo.domain.cupom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.domain.cupom.model.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long>{

}
