package br.com.casadocodigo.domain.estado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.casadocodigo.domain.pais.model.Pais;
import lombok.Getter;

@Entity
@Getter
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	private String nome;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Pais pais;

	@Deprecated
	public Estado() {}

	public Estado(String nome, Pais pais) {
		this.nome = nome;
		this.pais = pais;
	}
	
	void setId(Long id) {
		this.id = id;
	}
}
