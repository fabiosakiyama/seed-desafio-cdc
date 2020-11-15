package br.com.casadocodigo.domain.cupom.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;

@Entity
@Getter
public class Cupom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	private String codigo;
	
	@NotNull
	@Positive
	@Max(value = 100)
	private Integer percentualDesconto;
	
	@NotNull
	@Future
	private LocalDate validadeDoCupom;
	
	@Deprecated
	public Cupom() {}

	public Cupom(String codigo, Integer percentualDesconto, LocalDate validadeDoCupom) {
		this.codigo = codigo;
		this.percentualDesconto = percentualDesconto;
		this.validadeDoCupom = validadeDoCupom;
	}
}
