package br.com.casadocodigo.estado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.casadocodigo.pais.model.Pais;

@Entity
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	private Pais pais;

	@Deprecated
	public Estado() {}

	public Estado(String nome, Pais pais) {
		this.nome = nome;
		this.pais = pais;
	}
}
