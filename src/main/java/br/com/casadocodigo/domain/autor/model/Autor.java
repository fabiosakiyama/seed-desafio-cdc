package br.com.casadocodigo.domain.autor.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
@Getter
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column
	private String nome;
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull
	@Column(length = 400)
	private String descricao;
	
	@NotNull
	@Column
	private Instant instanteRegistrado;
	
	@Deprecated
	public Autor() {}

	public Autor(String nome, String email, String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.instanteRegistrado = Instant.now();
	}
}
