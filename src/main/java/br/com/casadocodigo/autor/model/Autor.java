package br.com.casadocodigo.autor.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import lombok.Getter;

@Entity
@Getter
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false, length = 400)
	private String descricao;
	
	@Column(nullable = false)
	private Instant instanteRegistrado;

	public Autor(String nome, @Email String email, String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.instanteRegistrado = Instant.now();
	}
}
