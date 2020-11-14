package br.com.casadocodigo.domain.pais.model;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextLong;

public class PaisBuilder {

	private Pais pais;
	
	private PaisBuilder() {
		this.pais = new Pais(randomAlphanumeric(10));
	}
	
	public static PaisBuilder builder() {
		PaisBuilder builder = new PaisBuilder();
		return builder;
	}
	
	public PaisBuilder aleatorio() {
		this.pais.setId(nextLong());
	    return this;
	}
	
	public Pais build() {
		return this.pais;
	}

}
