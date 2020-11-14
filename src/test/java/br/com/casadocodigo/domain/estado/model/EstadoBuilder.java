package br.com.casadocodigo.domain.estado.model;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import br.com.casadocodigo.domain.pais.model.PaisBuilder;

public class EstadoBuilder {

	private Estado estado;
	
	private EstadoBuilder() {
		this.estado = new Estado(randomAlphanumeric(10), PaisBuilder.builder().aleatorio().build());
	}
	
	public static EstadoBuilder builder() {
		EstadoBuilder builder = new EstadoBuilder();
		return builder;
	}
	
	public EstadoBuilder aleatorio() {
		this.estado.setId(nextLong());
	    return this;
	}
	
	public Estado build() {
		return this.estado;
	}

}
