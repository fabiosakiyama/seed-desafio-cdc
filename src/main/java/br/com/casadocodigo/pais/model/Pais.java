package br.com.casadocodigo.pais.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.casadocodigo.estado.model.Estado;
import lombok.Getter;

@Entity
@Getter
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nome;
	
	@OneToMany(mappedBy = "pais", orphanRemoval = true)
	private List<Estado> estados;
	
	@Deprecated
	public Pais() {}

	public Pais(String nome) {
		this.nome = nome;
	}
}
