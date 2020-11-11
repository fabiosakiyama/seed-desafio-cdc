package br.com.casadocodigo.categoria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nome;
	
	@Deprecated
	public Categoria() {}

	public Categoria(String nome) {
		this.nome = nome;
	}
}
