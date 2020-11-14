package br.com.casadocodigo.builder;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import br.com.casadocodigo.domain.pagamento.dto.NovoPagamentoRequest;

public class NovoPagamentoRequestBuilder {
	
	private NovoPagamentoRequest request;
	
	private NovoPagamentoRequestBuilder() {
		this.request = new NovoPagamentoRequest();
	}
	
	public static NovoPagamentoRequestBuilder builder() {
		NovoPagamentoRequestBuilder builder = new NovoPagamentoRequestBuilder();
		return builder;
	}
	
	public NovoPagamentoRequestBuilder aleatorio() {
		this.request.setEmail(randomAlphanumeric(10) + "@" + randomAlphanumeric(4) + "." + randomAlphanumeric(4));
		this.request.setNome(randomAlphanumeric(10));
		this.request.setSobreNome(randomAlphanumeric(10));
		this.request.setDocumento(randomAlphanumeric(3) + "." + randomAlphanumeric(3) + "." + randomAlphanumeric(3) + "-" + randomAlphanumeric(2));
		this.request.setEndereco(randomAlphanumeric(30));
		this.request.setComplemento(randomAlphanumeric(20));
		this.request.setCidade(randomAlphanumeric(20));
		this.request.setPaisId(nextLong());
		this.request.setTelefone(randomAlphanumeric(20));
		this.request.setCep(randomAlphanumeric(20));
	    return this;
	}
	
	public NovoPagamentoRequest build() {
		return this.request;
	}
}
